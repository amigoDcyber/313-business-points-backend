package com.businesspoint.backend.referrals.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "referrals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Referral extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "referred_id", nullable = false)
    private User referred;

    @Column(nullable = false)
    private String status;

    @Column(name = "reward_amount", nullable = false)
    private BigDecimal rewardAmount;
}
