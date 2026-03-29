package com.businesspoint.backend.quotes.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class QuoteResponse {
    private UUID id;
    private UUID userId;
    private UUID corridorId;
    private UUID exchangeRateId;
    private BigDecimal amountSent;
    private BigDecimal amountReceived;
    private BigDecimal fee;
    private BigDecimal totalPayable;
    private String sendCurrency;
    private String receiveCurrency;
    private String status;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
}
