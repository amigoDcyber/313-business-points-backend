package com.businesspoint.backend.users.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.users.dto.UserRequest;
import com.businesspoint.backend.users.dto.UserResponse;
import com.businesspoint.backend.users.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'COMPLIANCE')")
    public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserById(id)));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.ok(ApiResponse.success(userService.getAllUsers()));
    }

    @PutMapping("/{id}")
    @AuditAction(action = "UPDATE_USER_PROFILE", entityType = "USER")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable UUID id, @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(ApiResponse.success(userService.updateUser(id, request)));
    }
}
