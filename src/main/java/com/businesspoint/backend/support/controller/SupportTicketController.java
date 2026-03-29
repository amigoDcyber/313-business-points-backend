package com.businesspoint.backend.support.controller;

import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.support.dto.SupportTicketRequest;
import com.businesspoint.backend.support.dto.SupportTicketResponse;
import com.businesspoint.backend.support.service.SupportTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/support")
@RequiredArgsConstructor
public class SupportTicketController {

    private final SupportTicketService ticketService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<SupportTicketResponse>>> getMyTickets(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.getUserTickets(userDetails.getUser().getId())));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> createTicket(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody SupportTicketRequest request) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.createTicket(userDetails.getUser().getId(), request)));
    }

    @PatchMapping("/{id}/resolve")
    @PreAuthorize("hasAnyRole('ADMIN', 'OPS')")
    public ResponseEntity<ApiResponse<SupportTicketResponse>> resolveTicket(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(ticketService.resolveTicket(id)));
    }
}
