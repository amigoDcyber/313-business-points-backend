package com.businesspoint.backend.kyc.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.common.enums.KycStatus;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "kyc_verifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KycVerification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "id_type", nullable = false)
    private String idType;

    @Column(name = "id_number", nullable = false)
    private String idNumber;

    @Column(name = "id_document_url")
    private String idDocumentUrl;

    @Column(name = "selfie_url")
    private String selfieUrl;

    @Column(name = "proof_of_address_url")
    private String proofOfAddressUrl;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "occupation")
    private String occupation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus status;

    @Column(name = "rejection_reason", columnDefinition = "TEXT")
    private String rejectionReason;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;
}
