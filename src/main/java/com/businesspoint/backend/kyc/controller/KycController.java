package com.businesspoint.backend.kyc.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.common.enums.KycStatus;
import com.businesspoint.backend.kyc.dto.KycRequest;
import com.businesspoint.backend.kyc.dto.KycResponse;
import com.businesspoint.backend.kyc.service.KycService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/kyc")
@RequiredArgsConstructor
public class KycController {

    private final KycService kycService;

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'COMPLIANCE')")
    public ResponseEntity<ApiResponse<KycResponse>> getUserKyc(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(kycService.getKycByUserId(userId)));
    }

    @PostMapping("/user/{userId}")
    @AuditAction(action = "SUBMIT_KYC", entityType = "KYC_VERIFICATION")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<KycResponse>> submitKyc(@PathVariable UUID userId, @Valid @RequestBody KycRequest request) {
        return ResponseEntity.ok(ApiResponse.success(kycService.submitKyc(userId, request)));
    }

    @PatchMapping("/{kycId}/review")
    @AuditAction(action = "REVIEW_KYC", entityType = "KYC_VERIFICATION")
    @PreAuthorize("hasRole('COMPLIANCE')")
    public ResponseEntity<ApiResponse<KycResponse>> reviewKyc(
            @PathVariable UUID kycId,
            @RequestParam KycStatus status,
            @RequestParam(required = false) String reason) {
        return ResponseEntity.ok(ApiResponse.success(kycService.reviewKyc(kycId, status, reason)));
    }
}
