package com.businesspoint.backend.ledger.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class LedgerEntryResponse {
    private UUID id;
    private UUID transferId;
    private String entryType;
    private String accountDebit;
    private String accountCredit;
    private BigDecimal amount;
    private String currency;
    private String description;
    private LocalDateTime createdAt;
}
