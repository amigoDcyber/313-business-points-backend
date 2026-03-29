package com.businesspoint.backend.quotes.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quote extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_rate_id", nullable = false)
    private ExchangeRate exchangeRate;

    @Column(name = "amount_sent", nullable = false)
    private BigDecimal amountSent;

    @Column(name = "amount_received", nullable = false)
    private BigDecimal amountReceived;

    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    @Column(name = "total_payable", nullable = false)
    private BigDecimal totalPayable;

    @Column(name = "send_currency", nullable = false)
    private String sendCurrency;

    @Column(name = "receive_currency", nullable = false)
    private String receiveCurrency;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;
}
