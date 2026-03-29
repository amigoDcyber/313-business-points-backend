package com.businesspoint.backend.support.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SupportTicketResponse {
    private UUID id;
    private UUID userId;
    private UUID transferId;
    private String subject;
    private String status;
    private String priority;
    private LocalDateTime resolvedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
