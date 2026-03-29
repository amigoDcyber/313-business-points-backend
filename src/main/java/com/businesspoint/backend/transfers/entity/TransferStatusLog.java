package com.businesspoint.backend.transfers.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transfer_status_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferStatusLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferStatus status;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;
}
