package com.businesspoint.backend.notifications.mapper;

import com.businesspoint.backend.notifications.dto.NotificationRequest;
import com.businesspoint.backend.notifications.dto.NotificationResponse;
import com.businesspoint.backend.notifications.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "transfer.id", target = "transferId")
    NotificationResponse toResponse(Notification entity);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "transfer", ignore = true)
    Notification toEntity(NotificationRequest request);
}
