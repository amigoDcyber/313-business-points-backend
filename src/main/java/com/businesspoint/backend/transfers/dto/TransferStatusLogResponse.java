package com.businesspoint.backend.transfers.dto;

import com.businesspoint.backend.common.enums.TransferStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransferStatusLogResponse {
    private UUID id;
    private UUID transferId;
    private TransferStatus status;
    private String note;
    private LocalDateTime createdAt;
}
