package com.businesspoint.backend.exchange_rates.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.corridors.repository.CorridorRepository;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateRequest;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateResponse;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import com.businesspoint.backend.exchange_rates.mapper.ExchangeRateMapper;
import com.businesspoint.backend.exchange_rates.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final CorridorRepository corridorRepository;
    private final ExchangeRateMapper exchangeRateMapper;

    @Transactional(readOnly = true)
    public ExchangeRateResponse getLatestRate(UUID corridorId) {
        return exchangeRateRepository.findTopByCorridorIdOrderByFetchedAtDesc(corridorId)
                .map(exchangeRateMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("No exchange rate found for this corridor"));
    }

    @Transactional
    public ExchangeRateResponse updateRate(ExchangeRateRequest request) {
        Corridor corridor = corridorRepository.findById(request.getCorridorId())
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found"));

        ExchangeRate rate = exchangeRateMapper.toEntity(request);
        rate.setCorridor(corridor);
        rate.setFetchedAt(LocalDateTime.now());
        
        return exchangeRateMapper.toResponse(exchangeRateRepository.save(rate));
    }
}
