package com.businesspoint.backend.kyc.mapper;

import com.businesspoint.backend.kyc.dto.KycRequest;
import com.businesspoint.backend.kyc.dto.KycResponse;
import com.businesspoint.backend.kyc.entity.KycVerification;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class KycMapperImpl implements KycMapper {

    @Override
    public KycResponse toResponse(KycVerification entity) {
        if ( entity == null ) {
            return null;
        }

        KycResponse kycResponse = new KycResponse();

        kycResponse.setUserId( entityUserId( entity ) );
        kycResponse.setId( entity.getId() );
        kycResponse.setIdType( entity.getIdType() );
        kycResponse.setIdNumber( entity.getIdNumber() );
        kycResponse.setIdDocumentUrl( entity.getIdDocumentUrl() );
        kycResponse.setSelfieUrl( entity.getSelfieUrl() );
        kycResponse.setProofOfAddressUrl( entity.getProofOfAddressUrl() );
        kycResponse.setNationality( entity.getNationality() );
        kycResponse.setOccupation( entity.getOccupation() );
        kycResponse.setStatus( entity.getStatus() );
        kycResponse.setRejectionReason( entity.getRejectionReason() );
        kycResponse.setVerifiedAt( entity.getVerifiedAt() );
        kycResponse.setCreatedAt( entity.getCreatedAt() );
        kycResponse.setUpdatedAt( entity.getUpdatedAt() );

        return kycResponse;
    }

    @Override
    public KycVerification toEntity(KycRequest request) {
        if ( request == null ) {
            return null;
        }

        KycVerification.KycVerificationBuilder kycVerification = KycVerification.builder();

        kycVerification.idType( request.getIdType() );
        kycVerification.idNumber( request.getIdNumber() );
        kycVerification.idDocumentUrl( request.getIdDocumentUrl() );
        kycVerification.selfieUrl( request.getSelfieUrl() );
        kycVerification.proofOfAddressUrl( request.getProofOfAddressUrl() );
        kycVerification.nationality( request.getNationality() );
        kycVerification.occupation( request.getOccupation() );

        return kycVerification.build();
    }

    @Override
    public void updateEntity(KycVerification kyc, KycRequest request) {
        if ( request == null ) {
            return;
        }

        kyc.setIdType( request.getIdType() );
        kyc.setIdNumber( request.getIdNumber() );
        kyc.setIdDocumentUrl( request.getIdDocumentUrl() );
        kyc.setSelfieUrl( request.getSelfieUrl() );
        kyc.setProofOfAddressUrl( request.getProofOfAddressUrl() );
        kyc.setNationality( request.getNationality() );
        kyc.setOccupation( request.getOccupation() );
    }

    private UUID entityUserId(KycVerification kycVerification) {
        if ( kycVerification == null ) {
            return null;
        }
        User user = kycVerification.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
