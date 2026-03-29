package com.businesspoint.backend.audit.mapper;

import com.businesspoint.backend.audit.dto.AuditLogResponse;
import com.businesspoint.backend.audit.entity.AuditLog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class AuditLogMapperImpl implements AuditLogMapper {

    @Override
    public AuditLogResponse toResponse(AuditLog entity) {
        if ( entity == null ) {
            return null;
        }

        AuditLogResponse auditLogResponse = new AuditLogResponse();

        auditLogResponse.setId( entity.getId() );
        auditLogResponse.setUserId( entity.getUserId() );
        auditLogResponse.setAction( entity.getAction() );
        auditLogResponse.setEntityType( entity.getEntityType() );
        auditLogResponse.setEntityId( entity.getEntityId() );
        auditLogResponse.setIpAddress( entity.getIpAddress() );
        auditLogResponse.setDeviceInfo( entity.getDeviceInfo() );
        auditLogResponse.setCreatedAt( entity.getCreatedAt() );

        return auditLogResponse;
    }
}
