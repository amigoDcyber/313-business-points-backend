package com.businesspoint.backend.payouts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PayoutRequest {
    @NotNull(message = "Transfer ID is required")
    private UUID transferId;

    @NotBlank(message = "Provider is required")
    private String provider;

    private String providerRef;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    private String currency;
}
