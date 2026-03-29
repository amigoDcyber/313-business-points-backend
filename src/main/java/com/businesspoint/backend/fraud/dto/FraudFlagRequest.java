package com.businesspoint.backend.fraud.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class FraudFlagRequest {
    private UUID transferId;

    @NotBlank(message = "Rule triggered is required")
    private String ruleTriggered;

    @NotBlank(message = "Risk level is required")
    private String riskLevel;

    private String resolution;
}
