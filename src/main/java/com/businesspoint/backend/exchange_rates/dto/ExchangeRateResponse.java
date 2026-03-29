package com.businesspoint.backend.exchange_rates.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ExchangeRateResponse {
    private UUID id;
    private UUID corridorId;
    private BigDecimal bpRate;
    private BigDecimal bankRate;
    private BigDecimal spread;
    private BigDecimal feeFixed;
    private BigDecimal feePercent;
    private LocalDateTime validUntil;
    private LocalDateTime fetchedAt;
    private LocalDateTime createdAt;
}
