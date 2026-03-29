package com.businesspoint.backend.ledger.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.transfers.entity.Transfer;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ledger_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LedgerEntry extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;

    @Column(name = "entry_type", nullable = false)
    private String entryType;

    @Column(name = "account_debit", nullable = false)
    private String accountDebit;

    @Column(name = "account_credit", nullable = false)
    private String accountCredit;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency;

    @Column(columnDefinition = "TEXT")
    private String description;
}
