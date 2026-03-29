package com.businesspoint.backend.payouts.mapper;

import com.businesspoint.backend.payouts.dto.PayoutRequest;
import com.businesspoint.backend.payouts.dto.PayoutResponse;
import com.businesspoint.backend.payouts.entity.Payout;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PayoutMapper {
    @Mapping(source = "transfer.id", target = "transferId")
    PayoutResponse toResponse(Payout entity);
    
    @Mapping(target = "transfer", ignore = true)
    Payout toEntity(PayoutRequest request);
    
    @Mapping(target = "transfer", ignore = true)
    void updateEntity(@MappingTarget Payout payout, PayoutRequest request);
}
