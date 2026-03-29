package com.businesspoint.backend.exchange_rates.mapper;

import com.businesspoint.backend.exchange_rates.dto.ExchangeRateRequest;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateResponse;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExchangeRateMapper {
    @Mapping(source = "corridor.id", target = "corridorId")
    ExchangeRateResponse toResponse(ExchangeRate entity);
    
    @Mapping(target = "corridor", ignore = true)
    ExchangeRate toEntity(ExchangeRateRequest request);
    
    @Mapping(target = "corridor", ignore = true)
    void updateEntity(@MappingTarget ExchangeRate exchangeRate, ExchangeRateRequest request);
}
