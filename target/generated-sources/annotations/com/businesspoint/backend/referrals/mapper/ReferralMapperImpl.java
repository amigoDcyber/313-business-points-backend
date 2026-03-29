package com.businesspoint.backend.referrals.mapper;

import com.businesspoint.backend.referrals.dto.ReferralRequest;
import com.businesspoint.backend.referrals.dto.ReferralResponse;
import com.businesspoint.backend.referrals.entity.Referral;
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
public class ReferralMapperImpl implements ReferralMapper {

    @Override
    public ReferralResponse toResponse(Referral entity) {
        if ( entity == null ) {
            return null;
        }

        ReferralResponse referralResponse = new ReferralResponse();

        referralResponse.setReferrerId( entityReferrerId( entity ) );
        referralResponse.setReferredId( entityReferredId( entity ) );
        referralResponse.setId( entity.getId() );
        referralResponse.setStatus( entity.getStatus() );
        referralResponse.setRewardAmount( entity.getRewardAmount() );
        referralResponse.setCreatedAt( entity.getCreatedAt() );
        referralResponse.setUpdatedAt( entity.getUpdatedAt() );

        return referralResponse;
    }

    @Override
    public Referral toEntity(ReferralRequest request) {
        if ( request == null ) {
            return null;
        }

        Referral.ReferralBuilder referral = Referral.builder();

        referral.rewardAmount( request.getRewardAmount() );

        return referral.build();
    }

    @Override
    public void updateEntity(Referral referral, ReferralRequest request) {
        if ( request == null ) {
            return;
        }

        referral.setRewardAmount( request.getRewardAmount() );
    }

    private UUID entityReferrerId(Referral referral) {
        if ( referral == null ) {
            return null;
        }
        User referrer = referral.getReferrer();
        if ( referrer == null ) {
            return null;
        }
        UUID id = referrer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityReferredId(Referral referral) {
        if ( referral == null ) {
            return null;
        }
        User referred = referral.getReferred();
        if ( referred == null ) {
            return null;
        }
        UUID id = referred.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
