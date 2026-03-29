package com.businesspoint.backend.fraud.service;

import com.businesspoint.backend.common.enums.FraudStatus;
import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.fraud.dto.FraudFlagRequest;
import com.businesspoint.backend.fraud.dto.FraudFlagResponse;
import com.businesspoint.backend.fraud.entity.FraudFlag;
import com.businesspoint.backend.fraud.mapper.FraudFlagMapper;
import com.businesspoint.backend.fraud.repository.FraudFlagRepository;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.repository.TransferRepository;
import com.businesspoint.backend.transfers.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FraudService {

    private final FraudFlagRepository fraudFlagRepository;
    private final TransferRepository transferRepository;
    private final TransferService transferService;
    private final FraudFlagMapper fraudFlagMapper;

    @Transactional
    public FraudFlagResponse flagTransfer(FraudFlagRequest request) {
        Transfer transfer = transferRepository.findById(request.getTransferId())
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        FraudFlag flag = fraudFlagMapper.toEntity(request);
        flag.setTransfer(transfer);
        flag.setUser(transfer.getUser());
        flag.setStatus(FraudStatus.REVIEWING);
        
        // Automatically put transfer into compliance review
        transferService.updateTransferStatus(transfer.getId(), TransferStatus.COMPLIANCE_REVIEW, "Transfer flagged by AML/Fraud system for rule: " + request.getRuleTriggered());

        return fraudFlagMapper.toResponse(fraudFlagRepository.save(flag));
    }

    @Transactional
    public FraudFlagResponse resolveFlag(UUID flagId, FraudStatus status, String resolution) {
        FraudFlag flag = fraudFlagRepository.findById(flagId)
                .orElseThrow(() -> new ResourceNotFoundException("Fraud flag not found"));

        flag.setStatus(status);
        flag.setResolution(resolution);
        
        if (status == FraudStatus.CLEARED) {
            // Re-queue the transfer for processing if cleared
            transferService.updateTransferStatus(flag.getTransfer().getId(), TransferStatus.PROCESSING, "Fraud flag cleared: " + resolution);
        } else if (status == FraudStatus.BLOCKED) {
            // Cancel transfer if blocked permanently
            transferService.updateTransferStatus(flag.getTransfer().getId(), TransferStatus.CANCELLED, "Transfer blocked by Compliance: " + resolution);
        }

        return fraudFlagMapper.toResponse(fraudFlagRepository.save(flag));
    }
}
