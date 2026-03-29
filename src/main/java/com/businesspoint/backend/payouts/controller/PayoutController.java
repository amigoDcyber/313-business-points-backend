package com.businesspoint.backend.payouts.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.payouts.dto.PayoutRequest;
import com.businesspoint.backend.payouts.dto.PayoutResponse;
import com.businesspoint.backend.payouts.service.PayoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payouts")
@RequiredArgsConstructor
public class PayoutController {

    private final PayoutService payoutService;

    @PostMapping("/execute")
    @AuditAction(action = "EXECUTE_PAYOUT", entityType = "PAYOUT")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS')")
    public ResponseEntity<ApiResponse<PayoutResponse>> executePayout(@Valid @RequestBody PayoutRequest request) {
        return ResponseEntity.ok(ApiResponse.success(payoutService.executePayout(request)));
    }
}
