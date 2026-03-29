package com.businesspoint.backend.fraud.mapper;

import com.businesspoint.backend.fraud.dto.FraudFlagRequest;
import com.businesspoint.backend.fraud.dto.FraudFlagResponse;
import com.businesspoint.backend.fraud.entity.FraudFlag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FraudFlagMapper {
    @Mapping(source = "transfer.id", target = "transferId")
    @Mapping(source = "user.id", target = "userId")
    FraudFlagResponse toResponse(FraudFlag entity);
    
    @Mapping(target = "transfer", ignore = true)
    @Mapping(target = "user", ignore = true)
    FraudFlag toEntity(FraudFlagRequest request);
    
    @Mapping(target = "transfer", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget FraudFlag fraudFlag, FraudFlagRequest request);
}
