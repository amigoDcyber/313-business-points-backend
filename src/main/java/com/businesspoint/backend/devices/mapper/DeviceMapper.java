package com.businesspoint.backend.devices.mapper;

import com.businesspoint.backend.devices.dto.DeviceRequest;
import com.businesspoint.backend.devices.dto.DeviceResponse;
import com.businesspoint.backend.devices.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    @Mapping(source = "user.id", target = "userId")
    DeviceResponse toResponse(Device entity);
    
    @Mapping(target = "user", ignore = true)
    Device toEntity(DeviceRequest request);
    
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget Device device, DeviceRequest request);
}
