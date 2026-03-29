package com.businesspoint.backend.audit.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AuditLogResponse {
    private UUID id;
    private UUID userId;
    private String action;
    private String entityType;
    private UUID entityId;
    private String ipAddress;
    private String deviceInfo;
    private LocalDateTime createdAt;
}
