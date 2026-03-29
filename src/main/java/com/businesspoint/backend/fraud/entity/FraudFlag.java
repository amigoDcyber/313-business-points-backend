package com.businesspoint.backend.fraud.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.FraudStatus;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fraud_flags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudFlag extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id", nullable = false)
    private Transfer transfer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "rule_triggered", nullable = false)
    private String ruleTriggered;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FraudStatus status;

    @Column(columnDefinition = "TEXT")
    private String resolution;
}
