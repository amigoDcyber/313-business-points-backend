package com.businesspoint.backend.referrals.mapper;

import com.businesspoint.backend.referrals.dto.ReferralRequest;
import com.businesspoint.backend.referrals.dto.ReferralResponse;
import com.businesspoint.backend.referrals.entity.Referral;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReferralMapper {
    @Mapping(source = "referrer.id", target = "referrerId")
    @Mapping(source = "referred.id", target = "referredId")
    ReferralResponse toResponse(Referral entity);
    
    @Mapping(target = "referrer", ignore = true)
    @Mapping(target = "referred", ignore = true)
    Referral toEntity(ReferralRequest request);
    
    @Mapping(target = "referrer", ignore = true)
    @Mapping(target = "referred", ignore = true)
    void updateEntity(@MappingTarget Referral referral, ReferralRequest request);
}
