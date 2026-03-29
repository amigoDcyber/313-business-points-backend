package com.businesspoint.backend.exchange_rates.repository;

import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, UUID> {
    Optional<ExchangeRate> findTopByCorridorIdOrderByFetchedAtDesc(UUID corridorId);
}
