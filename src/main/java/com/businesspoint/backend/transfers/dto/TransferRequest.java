package com.businesspoint.backend.transfers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TransferRequest {
    @NotNull(message = "Quote ID is required")
    private UUID quoteId;

    @NotNull(message = "Recipient ID is required")
    private UUID recipientId;

    private String transferReason;
}
