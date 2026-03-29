package com.businesspoint.backend.support.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class SupportTicketRequest {
    private UUID transferId;

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Message content is required")
    private String initialMessage; // For the first message

    private String priority = "LOW";
}
