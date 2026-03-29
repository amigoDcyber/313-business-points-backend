package com.businesspoint.backend.quotes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class QuoteRequest {
    @NotNull(message = "Corridor ID is required")
    private UUID corridorId;

    @NotNull(message = "Send amount is required")
    private BigDecimal amountSent;

    @NotBlank(message = "Send currency is required")
    private String sendCurrency;

    @NotBlank(message = "Receive currency is required")
    private String receiveCurrency;
}
