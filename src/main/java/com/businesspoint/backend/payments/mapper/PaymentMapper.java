package com.businesspoint.backend.payments.mapper;

import com.businesspoint.backend.payments.dto.PaymentRequest;
import com.businesspoint.backend.payments.dto.PaymentResponse;
import com.businesspoint.backend.payments.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(source = "transfer.id", target = "transferId")
    @Mapping(source = "user.id", target = "userId")
    PaymentResponse toResponse(Payment entity);
    
    @Mapping(target = "transfer", ignore = true)
    @Mapping(target = "user", ignore = true)
    Payment toEntity(PaymentRequest request);
    
    @Mapping(target = "transfer", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget Payment payment, PaymentRequest request);
}
