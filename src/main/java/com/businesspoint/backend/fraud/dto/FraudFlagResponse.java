package com.businesspoint.backend.fraud.dto;

import com.businesspoint.backend.common.enums.FraudStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FraudFlagResponse {
    private UUID id;
    private UUID transferId;
    private UUID userId;
    private String ruleTriggered;
    private String riskLevel;
    private FraudStatus status;
    private String resolution;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
