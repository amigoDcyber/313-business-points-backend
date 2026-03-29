package com.businesspoint.backend.exchange_rates.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ExchangeRateRequest {
    @NotNull(message = "Corridor ID is required")
    private UUID corridorId;

    @NotNull(message = "BP rate is required")
    private BigDecimal bpRate;

    @NotNull(message = "Bank rate is required")
    private BigDecimal bankRate;

    @NotNull(message = "Spread is required")
    private BigDecimal spread;

    private BigDecimal feeFixed = BigDecimal.ZERO;
    private BigDecimal feePercent = BigDecimal.ZERO;

    @NotNull(message = "Valid until timestamp is required")
    private LocalDateTime validUntil;
}
