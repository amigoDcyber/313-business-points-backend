package com.businesspoint.backend.payment_methods.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodRequest;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodResponse;
import com.businesspoint.backend.payment_methods.service.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment-methods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<PaymentMethodResponse>>> getMyPaymentMethods(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.getUserPaymentMethods(userDetails.getUser().getId())));
    }

    @PostMapping
    @AuditAction(action = "ADD_PAYMENT_METHOD", entityType = "PAYMENT_METHOD")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<PaymentMethodResponse>> addPaymentMethod(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(ApiResponse.success(paymentMethodService.addPaymentMethod(userDetails.getUser().getId(), request)));
    }

    @DeleteMapping("/{id}")
    @AuditAction(action = "DELETE_PAYMENT_METHOD", entityType = "PAYMENT_METHOD")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<Void>> deletePaymentMethod(@PathVariable UUID id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
