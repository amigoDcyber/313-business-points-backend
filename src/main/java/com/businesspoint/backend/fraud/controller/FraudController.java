package com.businesspoint.backend.fraud.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.common.enums.FraudStatus;
import com.businesspoint.backend.fraud.dto.FraudFlagRequest;
import com.businesspoint.backend.fraud.dto.FraudFlagResponse;
import com.businesspoint.backend.fraud.service.FraudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/fraud")
@RequiredArgsConstructor
public class FraudController {

    private final FraudService fraudService;

    @PostMapping("/flag")
    @AuditAction(action = "SYSTEM_FLAG_FRAUD", entityType = "FRAUD_FLAG")
    @PreAuthorize("hasRole('COMPLIANCE')")
    public ResponseEntity<ApiResponse<FraudFlagResponse>> flagTransfer(@Valid @RequestBody FraudFlagRequest request) {
        return ResponseEntity.ok(ApiResponse.success(fraudService.flagTransfer(request)));
    }

    @PatchMapping("/{id}/review")
    @AuditAction(action = "REVIEW_FRAUD_FLAG", entityType = "FRAUD_FLAG")
    @PreAuthorize("hasRole('COMPLIANCE')")
    public ResponseEntity<ApiResponse<FraudFlagResponse>> resolveFlag(
            @PathVariable UUID id,
            @RequestParam FraudStatus status,
            @RequestParam String resolution) {
        return ResponseEntity.ok(ApiResponse.success(fraudService.resolveFlag(id, status, resolution)));
    }
}
