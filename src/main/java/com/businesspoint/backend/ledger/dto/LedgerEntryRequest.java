package com.businesspoint.backend.ledger.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class LedgerEntryRequest {
    private UUID transferId;

    @NotBlank(message = "Entry type is required")
    private String entryType;

    @NotBlank(message = "Debit account is required")
    private String accountDebit;

    @NotBlank(message = "Credit account is required")
    private String accountCredit;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "Currency is required")
    private String currency;

    private String description;
}
