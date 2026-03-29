package com.businesspoint.backend.payouts.dto;

import com.businesspoint.backend.common.enums.PayoutStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PayoutResponse {
    private UUID id;
    private UUID transferId;
    private String provider;
    private String providerRef;
    private BigDecimal amount;
    private String currency;
    private PayoutStatus status;
    private String failureReason;
    private LocalDateTime processedAt;
    private LocalDateTime createdAt;
}
