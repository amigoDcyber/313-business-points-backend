package com.businesspoint.backend.payouts.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.PayoutStatus;
import com.businesspoint.backend.transfers.entity.Transfer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payouts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payout extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "provider_ref")
    private String providerRef;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PayoutStatus status;

    @Column(name = "failure_reason", columnDefinition = "TEXT")
    private String failureReason;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;
}
