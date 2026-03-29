package com.businesspoint.backend.kyc.repository;

import com.businesspoint.backend.kyc.entity.KycVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KycVerificationRepository extends JpaRepository<KycVerification, UUID> {
    Optional<KycVerification> findByUserId(UUID userId);
}
