package com.businesspoint.backend.referrals.repository;

import com.businesspoint.backend.referrals.entity.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReferralRepository extends JpaRepository<Referral, UUID> {
    List<Referral> findAllByReferrerId(UUID referrerId);
    Optional<Referral> findByReferredId(UUID referredId);
}
