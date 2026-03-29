package com.businesspoint.backend.exchange_rates.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateRequest;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateResponse;
import com.businesspoint.backend.exchange_rates.service.ExchangeRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rates")
@RequiredArgsConstructor
public class ExchangeRateController {

    private final ExchangeRateService rateService;

    @GetMapping("/corridor/{corridorId}")
    public ResponseEntity<ApiResponse<ExchangeRateResponse>> getLatestRate(@PathVariable UUID corridorId) {
        return ResponseEntity.ok(ApiResponse.success(rateService.getLatestRate(corridorId)));
    }

    @PostMapping
    @AuditAction(action = "UPDATE_EXCHANGE_RATE", entityType = "EXCHANGE_RATE")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS')")
    public ResponseEntity<ApiResponse<ExchangeRateResponse>> updateRate(@Valid @RequestBody ExchangeRateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(rateService.updateRate(request)));
    }
}
