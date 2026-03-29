package com.businesspoint.backend.payouts.repository;

import com.businesspoint.backend.payouts.entity.Payout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PayoutRepository extends JpaRepository<Payout, UUID> {
    Optional<Payout> findByTransferId(UUID transferId);
}
