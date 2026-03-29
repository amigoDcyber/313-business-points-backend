package com.businesspoint.backend.payments.service;

import com.businesspoint.backend.common.enums.PaymentStatus;
import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.payments.dto.PaymentRequest;
import com.businesspoint.backend.payments.dto.PaymentResponse;
import com.businesspoint.backend.payments.entity.Payment;
import com.businesspoint.backend.payments.mapper.PaymentMapper;
import com.businesspoint.backend.payments.repository.PaymentRepository;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.repository.TransferRepository;
import com.businesspoint.backend.transfers.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransferRepository transferRepository;
    private final TransferService transferService;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) {
        Transfer transfer = transferRepository.findById(request.getTransferId())
                .orElseThrow(() -> new ResourceNotFoundException("Transfer not found"));

        if (transfer.getStatus() != TransferStatus.CREATED && transfer.getStatus() != TransferStatus.AWAITING_PAYMENT) {
            throw new BusinessException("Transfer is not in a valid state to receive payment");
        }

        Payment payment = paymentMapper.toEntity(request);
        payment.setTransfer(transfer);
        payment.setUser(transfer.getUser());
        
        // Simulating immediate capture hook
        payment.setStatus(PaymentStatus.CAPTURED);
        payment.setCapturedAt(LocalDateTime.now());
        
        payment = paymentRepository.save(payment);
        
        transferService.updateTransferStatus(transfer.getId(), TransferStatus.PAID, "Payment processed successfully via " + request.getProvider());
        transferService.updateTransferStatus(transfer.getId(), TransferStatus.PROCESSING, "Automated rule pushed Transfer to Processing queue");

        return paymentMapper.toResponse(payment);
    }
}
