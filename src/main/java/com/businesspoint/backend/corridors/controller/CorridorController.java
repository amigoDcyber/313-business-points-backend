package com.businesspoint.backend.corridors.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.corridors.dto.CorridorRequest;
import com.businesspoint.backend.corridors.dto.CorridorResponse;
import com.businesspoint.backend.corridors.service.CorridorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/corridors")
@RequiredArgsConstructor
public class CorridorController {

    private final CorridorService corridorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CorridorResponse>>> getActiveCorridors() {
        return ResponseEntity.ok(ApiResponse.success(corridorService.getActiveCorridors()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CorridorResponse>> getCorridorById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(corridorService.getCorridorById(id)));
    }

    @PostMapping
    @AuditAction(action = "CREATE_CORRIDOR", entityType = "CORRIDOR")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS')")
    public ResponseEntity<ApiResponse<CorridorResponse>> createCorridor(@Valid @RequestBody CorridorRequest request) {
        return ResponseEntity.ok(ApiResponse.success(corridorService.createCorridor(request)));
    }

    @PutMapping("/{id}")
    @AuditAction(action = "UPDATE_CORRIDOR", entityType = "CORRIDOR")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS')")
    public ResponseEntity<ApiResponse<CorridorResponse>> updateCorridor(@PathVariable UUID id, @Valid @RequestBody CorridorRequest request) {
        return ResponseEntity.ok(ApiResponse.success(corridorService.updateCorridor(id, request)));
    }
}
