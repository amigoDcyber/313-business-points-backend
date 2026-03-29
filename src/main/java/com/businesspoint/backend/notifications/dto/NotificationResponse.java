package com.businesspoint.backend.notifications.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationResponse {
    private UUID id;
    private UUID userId;
    private UUID transferId;
    private String type;
    private String channel;
    private String title;
    private String message;
    private Boolean isRead;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
