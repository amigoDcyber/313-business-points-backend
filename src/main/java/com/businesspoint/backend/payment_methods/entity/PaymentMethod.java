package com.businesspoint.backend.payment_methods.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_methods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String type;

    @Column(name = "provider_token")
    private String providerToken;

    @Column(name = "card_last4")
    private String cardLast4;

    @Column(name = "card_brand")
    private String cardBrand;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "is_default")
    private Boolean isDefault;
}
