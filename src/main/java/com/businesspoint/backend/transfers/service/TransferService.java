package com.businesspoint.backend.transfers.service;

import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.quotes.entity.Quote;
import com.businesspoint.backend.quotes.repository.QuoteRepository;
import com.businesspoint.backend.recipients.entity.Recipient;
import com.businesspoint.backend.recipients.repository.RecipientRepository;
import com.businesspoint.backend.transfers.dto.TransferRequest;
import com.businesspoint.backend.transfers.dto.TransferResponse;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.entity.TransferStatusLog;
import com.businesspoint.backend.transfers.mapper.TransferMapper;
import com.businesspoint.backend.transfers.repository.TransferRepository;
import com.businesspoint.backend.transfers.repository.TransferStatusLogRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;
    private final TransferStatusLogRepository statusLogRepository;
    private final QuoteRepository quoteRepository;
    private final RecipientRepository recipientRepository;
    private final UserRepository userRepository;
    private final TransferMapper transferMapper;
    private final StringRedisTemplate redisTemplate;

    @Transactional
    public TransferResponse initiateTransfer(UUID userId, TransferRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Quote quote = quoteRepository.findById(request.getQuoteId())
                .orElseThrow(() -> new ResourceNotFoundException("Quote not found"));

        // Validate Redis Lock Enforced Idempotency
        String lockKey = "quote_lock:" + quote.getId();
        if (Boolean.FALSE.equals(redisTemplate.hasKey(lockKey))) {
            throw new BusinessException("Quote has expired or is already consumed. Generate a new quote.");
        }

        Recipient recipient = recipientRepository.findById(request.getRecipientId())
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));

        // Proceed to generate safe transfer reference code
        String referenceCode = "BPT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        Transfer transfer = Transfer.builder()
                .user(user)
                .quote(quote)
                .recipient(recipient)
                .corridor(quote.getCorridor())
                .amountSent(quote.getAmountSent())
                .amountReceived(quote.getAmountReceived())
                .fee(quote.getFee())
                .totalPaid(quote.getTotalPayable())
                .sendCurrency(quote.getSendCurrency())
                .receiveCurrency(quote.getReceiveCurrency())
                .referenceCode(referenceCode)
                .transferReason(request.getTransferReason())
                .status(TransferStatus.CREATED)
                .build();

        transfer = transferRepository.save(transfer);
        
        logStatus(transfer, TransferStatus.CREATED, "Transfer initiated via quote");
        
        // Remove lock so quote cannot be reused
        redisTemplate.delete(lockKey);
        
        return transferMapper.toResponse(transfer);
    }
    
    @Transactional
    public void updateTransferStatus(UUID transferId, TransferStatus newStatus, String note) {
        Transfer transfer = transferRepository.findById(transferId)
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));
                
        transfer.setStatus(newStatus);
        transferRepository.save(transfer);
        
        logStatus(transfer, newStatus, note);
    }
    
    private void logStatus(Transfer transfer, TransferStatus status, String note) {
        TransferStatusLog log = TransferStatusLog.builder()
                .transfer(transfer)
                .status(status)
                .note(note)
                .build();
        statusLogRepository.save(log);
    }
}
