package com.businesspoint.backend.kyc.service;

import com.businesspoint.backend.common.enums.KycStatus;
import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.kyc.dto.KycRequest;
import com.businesspoint.backend.kyc.dto.KycResponse;
import com.businesspoint.backend.kyc.entity.KycVerification;
import com.businesspoint.backend.kyc.mapper.KycMapper;
import com.businesspoint.backend.kyc.repository.KycVerificationRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KycService {

    private final KycVerificationRepository kycRepository;
    private final UserRepository userRepository;
    private final KycMapper kycMapper;

    @Transactional(readOnly = true)
    public KycResponse getKycByUserId(UUID userId) {
        return kycRepository.findByUserId(userId)
                .map(kycMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("KYC missing for user"));
    }

    @Transactional
    public KycResponse submitKyc(UUID userId, KycRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        kycRepository.findByUserId(userId).ifPresent(existing -> {
            if (existing.getStatus() == KycStatus.PENDING || existing.getStatus() == KycStatus.VERIFIED) {
                throw new BusinessException("KYC is already submitted or verified");
            }
        });

        KycVerification kyc = kycMapper.toEntity(request);
        kyc.setUser(user);
        kyc.setStatus(KycStatus.PENDING);

        return kycMapper.toResponse(kycRepository.save(kyc));
    }

    @Transactional
    public KycResponse reviewKyc(UUID kycId, KycStatus status, String reason) {
        KycVerification kyc = kycRepository.findById(kycId)
                .orElseThrow(() -> new ResourceNotFoundException("KYC not found"));

        kyc.setStatus(status);
        if (status == KycStatus.REJECTED) {
            kyc.setRejectionReason(reason);
        } else if (status == KycStatus.VERIFIED) {
            kyc.setVerifiedAt(LocalDateTime.now());
        }

        return kycMapper.toResponse(kycRepository.save(kyc));
    }
}
