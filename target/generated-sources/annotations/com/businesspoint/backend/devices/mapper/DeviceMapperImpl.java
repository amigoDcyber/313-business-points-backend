package com.businesspoint.backend.devices.mapper;

import com.businesspoint.backend.devices.dto.DeviceRequest;
import com.businesspoint.backend.devices.dto.DeviceResponse;
import com.businesspoint.backend.devices.entity.Device;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceResponse toResponse(Device entity) {
        if ( entity == null ) {
            return null;
        }

        DeviceResponse deviceResponse = new DeviceResponse();

        deviceResponse.setUserId( entityUserId( entity ) );
        deviceResponse.setId( entity.getId() );
        deviceResponse.setDeviceId( entity.getDeviceId() );
        deviceResponse.setDeviceName( entity.getDeviceName() );
        deviceResponse.setPlatform( entity.getPlatform() );
        deviceResponse.setIsTrusted( entity.getIsTrusted() );
        deviceResponse.setLastSeen( entity.getLastSeen() );
        deviceResponse.setCreatedAt( entity.getCreatedAt() );

        return deviceResponse;
    }

    @Override
    public Device toEntity(DeviceRequest request) {
        if ( request == null ) {
            return null;
        }

        Device.DeviceBuilder device = Device.builder();

        device.deviceId( request.getDeviceId() );
        device.deviceName( request.getDeviceName() );
        device.platform( request.getPlatform() );
        device.isTrusted( request.getIsTrusted() );

        return device.build();
    }

    @Override
    public void updateEntity(Device device, DeviceRequest request) {
        if ( request == null ) {
            return;
        }

        device.setDeviceId( request.getDeviceId() );
        device.setDeviceName( request.getDeviceName() );
        device.setPlatform( request.getPlatform() );
        device.setIsTrusted( request.getIsTrusted() );
    }

    private UUID entityUserId(Device device) {
        if ( device == null ) {
            return null;
        }
        User user = device.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
