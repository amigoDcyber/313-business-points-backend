package com.businesspoint.backend.devices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeviceRequest {
    @NotBlank(message = "Device ID is required")
    private String deviceId;

    private String deviceName;
    private String platform;
    private Boolean isTrusted;
}
