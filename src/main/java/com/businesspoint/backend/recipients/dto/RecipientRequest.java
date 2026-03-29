package com.businesspoint.backend.recipients.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecipientRequest {
    @NotBlank(message = "Full name is required")
    private String fullName;

    private String phone;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "Delivery method is required")
    private String deliveryMethod;

    private String bankName;
    private String accountNumber;
    private String walletType;
    private String networkOperator;
    private Boolean isFavorite;
}
