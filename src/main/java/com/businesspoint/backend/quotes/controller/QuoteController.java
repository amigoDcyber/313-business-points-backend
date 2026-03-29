package com.businesspoint.backend.quotes.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.quotes.dto.QuoteRequest;
import com.businesspoint.backend.quotes.dto.QuoteResponse;
import com.businesspoint.backend.quotes.service.QuoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping
    @AuditAction(action = "GENERATE_QUOTE", entityType = "QUOTE")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<QuoteResponse>> generateQuote(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody QuoteRequest request) {
        return ResponseEntity.ok(ApiResponse.success(quoteService.generateQuote(userDetails.getUser().getId(), request)));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<QuoteResponse>> getQuote(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(quoteService.getQuote(id)));
    }
}
