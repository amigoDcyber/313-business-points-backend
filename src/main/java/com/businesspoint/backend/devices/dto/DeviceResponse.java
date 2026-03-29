package com.businesspoint.backend.devices.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DeviceResponse {
    private UUID id;
    private UUID userId;
    private String deviceId;
    private String deviceName;
    private String platform;
    private Boolean isTrusted;
    private LocalDateTime lastSeen;
    private LocalDateTime createdAt;
}
