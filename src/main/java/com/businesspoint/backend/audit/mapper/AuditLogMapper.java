package com.businesspoint.backend.audit.mapper;

import com.businesspoint.backend.audit.dto.AuditLogResponse;
import com.businesspoint.backend.audit.entity.AuditLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuditLogMapper {
    AuditLogResponse toResponse(AuditLog entity);
}
