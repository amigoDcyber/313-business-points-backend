package com.businesspoint.backend.notifications.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class NotificationRequest {
    private UUID transferId;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Channel is required")
    private String channel;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Message is required")
    private String message;
}
