package com.businesspoint.backend.payments.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.payments.dto.PaymentRequest;
import com.businesspoint.backend.payments.dto.PaymentResponse;
import com.businesspoint.backend.payments.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @AuditAction(action = "PROCESS_PAYMENT", entityType = "PAYMENT")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<PaymentResponse>> processPayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(ApiResponse.success(paymentService.processPayment(request)));
    }
}
