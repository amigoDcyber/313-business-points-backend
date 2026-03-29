package com.businesspoint.backend.payment_methods.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class PaymentMethodResponse {
    private UUID id;
    private UUID userId;
    private String type;
    private String providerToken;
    private String cardLast4;
    private String cardBrand;
    private String bankName;
    private Boolean isDefault;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
