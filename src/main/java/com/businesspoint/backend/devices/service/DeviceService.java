package com.businesspoint.backend.devices.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.devices.dto.DeviceResponse;
import com.businesspoint.backend.devices.entity.Device;
import com.businesspoint.backend.devices.mapper.DeviceMapper;
import com.businesspoint.backend.devices.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Transactional(readOnly = true)
    public List<DeviceResponse> getUserDevices(UUID userId) {
        return deviceRepository.findAllByUserId(userId).stream()
                .map(deviceMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public DeviceResponse markDeviceTrusted(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Device not found"));
                
        device.setIsTrusted(true);
        return deviceMapper.toResponse(deviceRepository.save(device));
    }
    
    @Transactional
    public void removeDevice(UUID deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
