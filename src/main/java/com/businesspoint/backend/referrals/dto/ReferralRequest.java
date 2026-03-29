package com.businesspoint.backend.referrals.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ReferralRequest {
    @NotNull(message = "Reward amount is required")
    private BigDecimal rewardAmount;
}
