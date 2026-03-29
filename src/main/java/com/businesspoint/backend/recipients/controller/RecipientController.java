package com.businesspoint.backend.recipients.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.recipients.dto.RecipientRequest;
import com.businesspoint.backend.recipients.dto.RecipientResponse;
import com.businesspoint.backend.recipients.service.RecipientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipients")
@RequiredArgsConstructor
public class RecipientController {

    private final RecipientService recipientService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<RecipientResponse>>> getMyRecipients(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(recipientService.getUserRecipients(userDetails.getUser().getId())));
    }

    @PostMapping
    @AuditAction(action = "CREATE_RECIPIENT", entityType = "RECIPIENT")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<RecipientResponse>> createRecipient(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody RecipientRequest request) {
        return ResponseEntity.ok(ApiResponse.success(recipientService.createRecipient(userDetails.getUser().getId(), request)));
    }

    @PutMapping("/{id}")
    @AuditAction(action = "UPDATE_RECIPIENT", entityType = "RECIPIENT")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<RecipientResponse>> updateRecipient(
            @PathVariable UUID id,
            @Valid @RequestBody RecipientRequest request) {
        return ResponseEntity.ok(ApiResponse.success(recipientService.updateRecipient(id, request)));
    }
}
