package com.businesspoint.backend.corridors.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CorridorRequest {
    @NotBlank(message = "From country is required")
    private String fromCountry;

    @NotBlank(message = "To country is required")
    private String toCountry;

    @NotBlank(message = "From currency is required")
    private String fromCurrency;

    @NotBlank(message = "To currency is required")
    private String toCurrency;

    @NotBlank(message = "Delivery method is required")
    private String deliveryMethod;

    private String deliveryEstimate;

    @NotNull(message = "Min amount is required")
    private BigDecimal minAmount;

    @NotNull(message = "Max amount is required")
    private BigDecimal maxAmount;

    private Boolean isActive;
}
