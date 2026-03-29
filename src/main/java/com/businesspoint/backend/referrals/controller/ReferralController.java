package com.businesspoint.backend.referrals.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.referrals.dto.ReferralRequest;
import com.businesspoint.backend.referrals.dto.ReferralResponse;
import com.businesspoint.backend.referrals.service.ReferralService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/referrals")
@RequiredArgsConstructor
public class ReferralController {

    private final ReferralService referralService;

    @PostMapping("/code/{code}")
    @AuditAction(action = "SUBMIT_REFERRAL_CODE", entityType = "REFERRAL")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<ReferralResponse>> applyReferral(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable String code,
            @Valid @RequestBody ReferralRequest request) {
        return ResponseEntity.ok(ApiResponse.success(referralService.processReferral(code, userDetails.getUser().getId(), request)));
    }

    @PatchMapping("/activate/{userId}")
    @AuditAction(action = "ACTIVATE_REFERRAL_REWARD", entityType = "REFERRAL")
    @PreAuthorize("hasAnyRole('SYSTEM', 'ADMIN')")
    public ResponseEntity<ApiResponse<ReferralResponse>> activateReferralReward(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(referralService.activateReferral(userId)));
    }
}
