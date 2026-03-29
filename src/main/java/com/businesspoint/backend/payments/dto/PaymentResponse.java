package com.businesspoint.backend.payments.dto;

import com.businesspoint.backend.common.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentResponse {
    private UUID id;
    private UUID transferId;
    private UUID userId;
    private String paymentMethod;
    private String provider;
    private String providerRef;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private LocalDateTime capturedAt;
    private LocalDateTime createdAt;
}
