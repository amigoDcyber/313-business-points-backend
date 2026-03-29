package com.businesspoint.backend.ledger.controller;

import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.ledger.entity.LedgerEntry;
import com.businesspoint.backend.ledger.service.LedgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ledger")
@RequiredArgsConstructor
public class LedgerController {

    private final LedgerService ledgerService;

    @GetMapping("/transfer/{transferId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS', 'COMPLIANCE')")
    public ResponseEntity<ApiResponse<List<LedgerEntry>>> getLedgerEntries(@PathVariable UUID transferId) {
        return ResponseEntity.ok(ApiResponse.success(ledgerService.getEntriesForTransfer(transferId)));
    }
}
