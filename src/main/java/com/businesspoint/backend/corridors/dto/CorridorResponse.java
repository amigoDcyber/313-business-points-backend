package com.businesspoint.backend.corridors.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CorridorResponse {
    private UUID id;
    private String fromCountry;
    private String toCountry;
    private String fromCurrency;
    private String toCurrency;
    private String deliveryMethod;
    private String deliveryEstimate;
    private BigDecimal minAmount;
    private BigDecimal maxAmount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
