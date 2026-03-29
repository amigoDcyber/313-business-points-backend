package com.businesspoint.backend.quotes.mapper;

import com.businesspoint.backend.quotes.dto.QuoteRequest;
import com.businesspoint.backend.quotes.dto.QuoteResponse;
import com.businesspoint.backend.quotes.entity.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "corridor.id", target = "corridorId")
    @Mapping(source = "exchangeRate.id", target = "exchangeRateId")
    QuoteResponse toResponse(Quote entity);
}
