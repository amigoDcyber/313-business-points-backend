package com.businesspoint.backend.payment_methods.repository;

import com.businesspoint.backend.payment_methods.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    List<PaymentMethod> findAllByUserId(UUID userId);
    Optional<PaymentMethod> findByUserIdAndIsDefaultTrue(UUID userId);
}
