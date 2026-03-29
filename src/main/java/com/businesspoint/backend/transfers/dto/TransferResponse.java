package com.businesspoint.backend.transfers.dto;

import com.businesspoint.backend.common.enums.TransferStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransferResponse {
    private UUID id;
    private UUID userId;
    private UUID quoteId;
    private UUID recipientId;
    private UUID corridorId;
    private BigDecimal amountSent;
    private BigDecimal amountReceived;
    private BigDecimal fee;
    private BigDecimal totalPaid;
    private String sendCurrency;
    private String receiveCurrency;
    private String transferReason;
    private TransferStatus status;
    private String referenceCode;
    private String receiptUrl;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
}
