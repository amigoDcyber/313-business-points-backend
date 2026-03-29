package com.businesspoint.backend.quotes.service;

import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.corridors.repository.CorridorRepository;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import com.businesspoint.backend.exchange_rates.repository.ExchangeRateRepository;
import com.businesspoint.backend.quotes.dto.QuoteRequest;
import com.businesspoint.backend.quotes.dto.QuoteResponse;
import com.businesspoint.backend.quotes.entity.Quote;
import com.businesspoint.backend.quotes.mapper.QuoteMapper;
import com.businesspoint.backend.quotes.repository.QuoteRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final CorridorRepository corridorRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final QuoteMapper quoteMapper;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public QuoteResponse generateQuote(UUID userId, QuoteRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Corridor corridor = corridorRepository.findById(request.getCorridorId())
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found"));

        ExchangeRate rate = exchangeRateRepository.findTopByCorridorIdOrderByFetchedAtDesc(corridor.getId())
                .orElseThrow(() -> new BusinessException("No valid rate for this corridor"));

        if (rate.getValidUntil().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Exchange rate expired");
        }

        BigDecimal amountSent = request.getAmountSent();
        if (amountSent.compareTo(corridor.getMinAmount()) < 0 || amountSent.compareTo(corridor.getMaxAmount()) > 0) {
            throw new BusinessException("Amount out of corridor bounds");
        }

        BigDecimal fee = rate.getFeeFixed().add(amountSent.multiply(rate.getFeePercent()).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP));
        BigDecimal amountReceived = amountSent.multiply(rate.getBpRate()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal totalPayable = amountSent.add(fee);

        Quote quote = Quote.builder()
                .user(user)
                .corridor(corridor)
                .exchangeRate(rate)
                .amountSent(amountSent)
                .amountReceived(amountReceived)
                .fee(fee)
                .totalPayable(totalPayable)
                .sendCurrency(request.getSendCurrency())
                .receiveCurrency(request.getReceiveCurrency())
                .status("LOCKED")
                .expiresAt(LocalDateTime.now().plusMinutes(15)) // 15 mins lock
                .build();

        quote = quoteRepository.save(quote);
        
        // Save to Redis to enforce distributed timeout lock
        String redisKey = "quote_lock:" + quote.getId();
        redisTemplate.opsForValue().set(redisKey, "locked", 15, TimeUnit.MINUTES);

        return quoteMapper.toResponse(quote);
    }
    
    @Transactional(readOnly = true)
    public QuoteResponse getQuote(UUID quoteId) {
        Quote quote = quoteRepository.findById(quoteId)
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));
                
        // Overwrite status if redis lock expired
        String redisKey = "quote_lock:" + quote.getId();
        if (Boolean.FALSE.equals(redisTemplate.hasKey(redisKey))) {
            quote.setStatus("EXPIRED");
        }
        
        return quoteMapper.toResponse(quote);
    }
}
