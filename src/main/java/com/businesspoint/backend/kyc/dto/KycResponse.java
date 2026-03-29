package com.businesspoint.backend.kyc.dto;

import com.businesspoint.backend.common.enums.KycStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class KycResponse {
    private UUID id;
    private UUID userId;
    private String idType;
    private String idNumber;
    private String idDocumentUrl;
    private String selfieUrl;
    private String proofOfAddressUrl;
    private String nationality;
    private String occupation;
    private KycStatus status;
    private String rejectionReason;
    private LocalDateTime verifiedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
