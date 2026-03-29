package com.businesspoint.backend.referrals.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReferralResponse {
    private UUID id;
    private UUID referrerId;
    private UUID referredId;
    private String status;
    private BigDecimal rewardAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
