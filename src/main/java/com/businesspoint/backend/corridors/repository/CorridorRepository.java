package com.businesspoint.backend.corridors.repository;

import com.businesspoint.backend.corridors.entity.Corridor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CorridorRepository extends JpaRepository<Corridor, UUID> {
    List<Corridor> findByIsActiveTrue();
    Optional<Corridor> findByFromCurrencyAndToCurrencyAndDeliveryMethod(String fromCurrency, String toCurrency, String deliveryMethod);
}
