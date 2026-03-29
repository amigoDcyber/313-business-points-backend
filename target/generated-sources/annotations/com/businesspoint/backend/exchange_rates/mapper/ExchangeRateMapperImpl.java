package com.businesspoint.backend.exchange_rates.mapper;

import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateRequest;
import com.businesspoint.backend.exchange_rates.dto.ExchangeRateResponse;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class ExchangeRateMapperImpl implements ExchangeRateMapper {

    @Override
    public ExchangeRateResponse toResponse(ExchangeRate entity) {
        if ( entity == null ) {
            return null;
        }

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

        exchangeRateResponse.setCorridorId( entityCorridorId( entity ) );
        exchangeRateResponse.setId( entity.getId() );
        exchangeRateResponse.setBpRate( entity.getBpRate() );
        exchangeRateResponse.setBankRate( entity.getBankRate() );
        exchangeRateResponse.setSpread( entity.getSpread() );
        exchangeRateResponse.setFeeFixed( entity.getFeeFixed() );
        exchangeRateResponse.setFeePercent( entity.getFeePercent() );
        exchangeRateResponse.setValidUntil( entity.getValidUntil() );
        exchangeRateResponse.setFetchedAt( entity.getFetchedAt() );
        exchangeRateResponse.setCreatedAt( entity.getCreatedAt() );

        return exchangeRateResponse;
    }

    @Override
    public ExchangeRate toEntity(ExchangeRateRequest request) {
        if ( request == null ) {
            return null;
        }

        ExchangeRate.ExchangeRateBuilder exchangeRate = ExchangeRate.builder();

        exchangeRate.bpRate( request.getBpRate() );
        exchangeRate.bankRate( request.getBankRate() );
        exchangeRate.spread( request.getSpread() );
        exchangeRate.feeFixed( request.getFeeFixed() );
        exchangeRate.feePercent( request.getFeePercent() );
        exchangeRate.validUntil( request.getValidUntil() );

        return exchangeRate.build();
    }

    @Override
    public void updateEntity(ExchangeRate exchangeRate, ExchangeRateRequest request) {
        if ( request == null ) {
            return;
        }

        exchangeRate.setBpRate( request.getBpRate() );
        exchangeRate.setBankRate( request.getBankRate() );
        exchangeRate.setSpread( request.getSpread() );
        exchangeRate.setFeeFixed( request.getFeeFixed() );
        exchangeRate.setFeePercent( request.getFeePercent() );
        exchangeRate.setValidUntil( request.getValidUntil() );
    }

    private UUID entityCorridorId(ExchangeRate exchangeRate) {
        if ( exchangeRate == null ) {
            return null;
        }
        Corridor corridor = exchangeRate.getCorridor();
        if ( corridor == null ) {
            return null;
        }
        UUID id = corridor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
