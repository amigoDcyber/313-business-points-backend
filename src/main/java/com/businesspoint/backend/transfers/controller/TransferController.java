package com.businesspoint.backend.transfers.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.transfers.dto.TransferRequest;
import com.businesspoint.backend.transfers.dto.TransferResponse;
import com.businesspoint.backend.transfers.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    @AuditAction(action = "INITIATE_TRANSFER", entityType = "TRANSFER")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<TransferResponse>> initiateTransfer(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody TransferRequest request) {
        return ResponseEntity.ok(ApiResponse.success(transferService.initiateTransfer(userDetails.getUser().getId(), request)));
    }
}
