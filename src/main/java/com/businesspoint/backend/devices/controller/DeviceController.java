package com.businesspoint.backend.devices.controller;

import com.businesspoint.backend.audit.annotation.AuditAction;
import com.businesspoint.backend.common.ApiResponse;
import com.businesspoint.backend.devices.dto.DeviceResponse;
import com.businesspoint.backend.devices.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<DeviceResponse>>> getUserDevices(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(deviceService.getUserDevices(userId)));
    }

    @PatchMapping("/{deviceId}/trust")
    @AuditAction(action = "MARK_DEVICE_TRUSTED", entityType = "DEVICE")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<DeviceResponse>> markDeviceTrusted(@PathVariable UUID deviceId) {
        return ResponseEntity.ok(ApiResponse.success(deviceService.markDeviceTrusted(deviceId)));
    }

    @DeleteMapping("/{deviceId}")
    @AuditAction(action = "REMOVE_DEVICE", entityType = "DEVICE")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<Void>> removeDevice(@PathVariable UUID deviceId) {
        deviceService.removeDevice(deviceId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
