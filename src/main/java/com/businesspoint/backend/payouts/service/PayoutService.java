package com.businesspoint.backend.payouts.service;

import com.businesspoint.backend.common.enums.PayoutStatus;
import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.payouts.dto.PayoutRequest;
import com.businesspoint.backend.payouts.dto.PayoutResponse;
import com.businesspoint.backend.payouts.entity.Payout;
import com.businesspoint.backend.payouts.mapper.PayoutMapper;
import com.businesspoint.backend.payouts.repository.PayoutRepository;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.repository.TransferRepository;
import com.businesspoint.backend.transfers.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PayoutService {

    private final PayoutRepository payoutRepository;
    private final TransferRepository transferRepository;
    private final TransferService transferService;
    private final PayoutMapper payoutMapper;

    @Transactional
    public PayoutResponse executePayout(PayoutRequest request) {
        Transfer transfer = transferRepository.findById(request.getTransferId())
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        if (transfer.getStatus() != TransferStatus.PROCESSING) {
            throw new BusinessException("Transfer must be in PROCESSING state to execute payout");
        }

        transferService.updateTransferStatus(transfer.getId(), TransferStatus.PAYOUT_IN_PROGRESS, "Payout queued to provider: " + request.getProvider());

        Payout payout = payoutMapper.toEntity(request);
        payout.setTransfer(transfer);
        
        // Simulating the direct synchronous external API handshake
        // In reality this might be Async via Queues/Webhooks
        payout.setStatus(PayoutStatus.PROCESSED);
        payout.setProcessedAt(LocalDateTime.now());
        payout.setProviderRef("PAY-" + UUID.randomUUID().toString().substring(0, 8));

        payout = payoutRepository.save(payout);
        
        transferService.updateTransferStatus(transfer.getId(), TransferStatus.COMPLETED, "Payout confirmed by provider");
        
        transfer.setCompletedAt(LocalDateTime.now());
        transfer.setReceiptUrl("https://receipts.313businesspoints.com/" + transfer.getReferenceCode());
        transferRepository.save(transfer);

        return payoutMapper.toResponse(payout);
    }
}
