package com.businesspoint.backend.kyc.mapper;

import com.businesspoint.backend.kyc.dto.KycRequest;
import com.businesspoint.backend.kyc.dto.KycResponse;
import com.businesspoint.backend.kyc.entity.KycVerification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface KycMapper {
    @Mapping(source = "user.id", target = "userId")
    KycResponse toResponse(KycVerification entity);
    
    @Mapping(target = "user", ignore = true)
    KycVerification toEntity(KycRequest request);
    
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget KycVerification kyc, KycRequest request);
}
