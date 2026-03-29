package com.businesspoint.backend.referrals.service;

import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.referrals.dto.ReferralRequest;
import com.businesspoint.backend.referrals.dto.ReferralResponse;
import com.businesspoint.backend.referrals.entity.Referral;
import com.businesspoint.backend.referrals.mapper.ReferralMapper;
import com.businesspoint.backend.referrals.repository.ReferralRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferralService {

    private final ReferralRepository referralRepository;
    private final UserRepository userRepository;
    private final ReferralMapper referralMapper;

    @Transactional
    public ReferralResponse processReferral(String referralCode, UUID referredUserId, ReferralRequest request) {
        User referrer = userRepository.findByReferralCode(referralCode)
                .orElseThrow(() -> new ResourceNotFoundException("Referrer code not found"));

        User referred = userRepository.findById(referredUserId)
                .orElseThrow(() -> new ResourceNotFoundException("Referred user not found"));

        if (referrer.getId().equals(referred.getId())) {
            throw new BusinessException("User cannot refer themselves");
        }
        
        if (referralRepository.findByReferredId(referredUserId).isPresent()) {
            throw new BusinessException("User was already referred");
        }

        Referral referral = referralMapper.toEntity(request);
        referral.setReferrer(referrer);
        referral.setReferred(referred);
        referral.setStatus("PENDING"); // Pending until first transfer is completed

        return referralMapper.toResponse(referralRepository.save(referral));
    }

    @Transactional
    public ReferralResponse activateReferral(UUID referredUserId) {
        Referral referral = referralRepository.findByReferredId(referredUserId)
                .orElseThrow(() -> new ResourceNotFoundException("No pending referral found for this user"));

        if (!"PENDING".equals(referral.getStatus())) {
            throw new BusinessException("Referral is not in a pending state");
        }

        referral.setStatus("REWARDED");
        
        // Business logic to actually deposit the rewardAmount into the referrer's wallet would go here.
        // E.g. ledgerService.recordReferralBonus(...)
        
        return referralMapper.toResponse(referralRepository.save(referral));
    }
}
