package com.businesspoint.backend.payment_methods.mapper;

import com.businesspoint.backend.payment_methods.dto.PaymentMethodRequest;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodResponse;
import com.businesspoint.backend.payment_methods.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {
    @Mapping(source = "user.id", target = "userId")
    PaymentMethodResponse toResponse(PaymentMethod entity);
    
    @Mapping(target = "user", ignore = true)
    PaymentMethod toEntity(PaymentMethodRequest request);
    
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget PaymentMethod paymentMethod, PaymentMethodRequest request);
}
