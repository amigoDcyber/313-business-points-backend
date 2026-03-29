package com.businesspoint.backend.transfers.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.TransferStatus;
import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.quotes.entity.Quote;
import com.businesspoint.backend.recipients.entity.Recipient;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quote_id")
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private Recipient recipient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corridor_id", nullable = false)
    private Corridor corridor;

    @Column(name = "amount_sent", nullable = false)
    private BigDecimal amountSent;

    @Column(name = "amount_received", nullable = false)
    private BigDecimal amountReceived;

    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    @Column(name = "total_paid", nullable = false)
    private BigDecimal totalPaid;

    @Column(name = "send_currency", nullable = false)
    private String sendCurrency;

    @Column(name = "receive_currency", nullable = false)
    private String receiveCurrency;

    @Column(name = "transfer_reason")
    private String transferReason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransferStatus status;

    @Column(name = "reference_code", nullable = false, unique = true)
    private String referenceCode;

    @Column(name = "receipt_url")
    private String receiptUrl;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}
