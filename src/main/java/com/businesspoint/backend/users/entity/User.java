package com.businesspoint.backend.users.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role status; // Storing role as status strictly as per blueprint for now, though role mapping is better

    @Column(name = "referral_code", unique = true)
    private String referralCode;

    @Column(name = "language")
    private String language;

    @Builder.Default
    @Column(nullable = false, precision = 15, scale = 2)
    private java.math.BigDecimal balance = new java.math.BigDecimal("1250.00");

    @Builder.Default
    @Column(nullable = false, length = 10)
    private String currency = "RWF";
}
