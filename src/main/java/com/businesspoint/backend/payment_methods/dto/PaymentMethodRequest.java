package com.businesspoint.backend.payment_methods.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentMethodRequest {
    @NotBlank(message = "Type is required")
    private String type;

    private String providerToken;
    private String cardLast4;
    private String cardBrand;
    private String bankName;
    private Boolean isDefault;
}
