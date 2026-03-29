package com.businesspoint.backend.kyc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KycRequest {
    @NotBlank(message = "ID Type is required")
    private String idType;

    @NotBlank(message = "ID Number is required")
    private String idNumber;

    private String idDocumentUrl;
    private String selfieUrl;
    private String proofOfAddressUrl;
    
    @NotBlank(message = "Nationality is required")
    private String nationality;
    
    private String occupation;
}
