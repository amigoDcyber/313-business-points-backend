package com.businesspoint.backend.recipients.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RecipientResponse {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String phone;
    private String country;
    private String deliveryMethod;
    private String bankName;
    private String accountNumber;
    private String walletType;
    private String networkOperator;
    private Boolean isFavorite;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
