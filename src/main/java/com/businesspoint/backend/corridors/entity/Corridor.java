package com.businesspoint.backend.corridors.entity;

import com.businesspoint.backend.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "corridors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Corridor extends BaseEntity {

    @Column(name = "from_country", nullable = false)
    private String fromCountry;

    @Column(name = "to_country", nullable = false)
    private String toCountry;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "delivery_method", nullable = false)
    private String deliveryMethod;

    @Column(name = "delivery_estimate")
    private String deliveryEstimate;

    @Column(name = "min_amount")
    private BigDecimal minAmount;

    @Column(name = "max_amount")
    private BigDecimal maxAmount;

    @Column(name = "is_active")
    private Boolean isActive;
}
