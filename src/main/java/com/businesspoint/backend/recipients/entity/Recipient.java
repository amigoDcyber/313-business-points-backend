package com.businesspoint.backend.recipients.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recipient extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "delivery_method", nullable = false)
    private String deliveryMethod;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "wallet_type")
    private String walletType;

    @Column(name = "network_operator")
    private String networkOperator;

    @Column(name = "is_favorite")
    private Boolean isFavorite;
}
