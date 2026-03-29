package com.businesspoint.backend.exchange_rates.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.corridors.entity.Corridor;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_rates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRate extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @Column(name = "bp_rate", nullable = false)
    private BigDecimal bpRate;

    @Column(name = "bank_rate", nullable = false)
    private BigDecimal bankRate;

    @Column(name = "spread", nullable = false)
    private BigDecimal spread;

    @Column(name = "fee_fixed", nullable = false)
    private BigDecimal feeFixed;

    @Column(name = "fee_percent", nullable = false)
    private BigDecimal feePercent;

    @Column(name = "valid_until", nullable = false)
    private LocalDateTime validUntil;

    @Column(name = "fetched_at", nullable = false)
    private LocalDateTime fetchedAt;
}
