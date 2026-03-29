package com.businesspoint.backend.fraud.mapper;

import com.businesspoint.backend.fraud.dto.FraudFlagRequest;
import com.businesspoint.backend.fraud.dto.FraudFlagResponse;
import com.businesspoint.backend.fraud.entity.FraudFlag;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class FraudFlagMapperImpl implements FraudFlagMapper {

    @Override
    public FraudFlagResponse toResponse(FraudFlag entity) {
        if ( entity == null ) {
            return null;
        }

        FraudFlagResponse fraudFlagResponse = new FraudFlagResponse();

        fraudFlagResponse.setTransferId( entityTransferId( entity ) );
        fraudFlagResponse.setUserId( entityUserId( entity ) );
        fraudFlagResponse.setId( entity.getId() );
        fraudFlagResponse.setRuleTriggered( entity.getRuleTriggered() );
        fraudFlagResponse.setRiskLevel( entity.getRiskLevel() );
        fraudFlagResponse.setStatus( entity.getStatus() );
        fraudFlagResponse.setResolution( entity.getResolution() );
        fraudFlagResponse.setCreatedAt( entity.getCreatedAt() );
        fraudFlagResponse.setUpdatedAt( entity.getUpdatedAt() );

        return fraudFlagResponse;
    }

    @Override
    public FraudFlag toEntity(FraudFlagRequest request) {
        if ( request == null ) {
            return null;
        }

        FraudFlag.FraudFlagBuilder fraudFlag = FraudFlag.builder();

        fraudFlag.ruleTriggered( request.getRuleTriggered() );
        fraudFlag.riskLevel( request.getRiskLevel() );
        fraudFlag.resolution( request.getResolution() );

        return fraudFlag.build();
    }

    @Override
    public void updateEntity(FraudFlag fraudFlag, FraudFlagRequest request) {
        if ( request == null ) {
            return;
        }

        fraudFlag.setRuleTriggered( request.getRuleTriggered() );
        fraudFlag.setRiskLevel( request.getRiskLevel() );
        fraudFlag.setResolution( request.getResolution() );
    }

    private UUID entityTransferId(FraudFlag fraudFlag) {
        if ( fraudFlag == null ) {
            return null;
        }
        Transfer transfer = fraudFlag.getTransfer();
        if ( transfer == null ) {
            return null;
        }
        UUID id = transfer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityUserId(FraudFlag fraudFlag) {
        if ( fraudFlag == null ) {
            return null;
        }
        User user = fraudFlag.getUser();
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
