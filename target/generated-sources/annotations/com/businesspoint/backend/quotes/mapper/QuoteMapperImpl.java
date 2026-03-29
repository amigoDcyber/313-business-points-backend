package com.businesspoint.backend.quotes.mapper;

import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.exchange_rates.entity.ExchangeRate;
import com.businesspoint.backend.quotes.dto.QuoteResponse;
import com.businesspoint.backend.quotes.entity.Quote;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class QuoteMapperImpl implements QuoteMapper {

    @Override
    public QuoteResponse toResponse(Quote entity) {
        if ( entity == null ) {
            return null;
        }

        QuoteResponse quoteResponse = new QuoteResponse();

        quoteResponse.setUserId( entityUserId( entity ) );
        quoteResponse.setCorridorId( entityCorridorId( entity ) );
        quoteResponse.setExchangeRateId( entityExchangeRateId( entity ) );
        quoteResponse.setId( entity.getId() );
        quoteResponse.setAmountSent( entity.getAmountSent() );
        quoteResponse.setAmountReceived( entity.getAmountReceived() );
        quoteResponse.setFee( entity.getFee() );
        quoteResponse.setTotalPayable( entity.getTotalPayable() );
        quoteResponse.setSendCurrency( entity.getSendCurrency() );
        quoteResponse.setReceiveCurrency( entity.getReceiveCurrency() );
        quoteResponse.setStatus( entity.getStatus() );
        quoteResponse.setExpiresAt( entity.getExpiresAt() );
        quoteResponse.setCreatedAt( entity.getCreatedAt() );

        return quoteResponse;
    }

    private UUID entityUserId(Quote quote) {
        if ( quote == null ) {
            return null;
        }
        User user = quote.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityCorridorId(Quote quote) {
        if ( quote == null ) {
            return null;
        }
        Corridor corridor = quote.getCorridor();
        if ( corridor == null ) {
            return null;
        }
        UUID id = corridor.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityExchangeRateId(Quote quote) {
        if ( quote == null ) {
            return null;
        }
        ExchangeRate exchangeRate = quote.getExchangeRate();
        if ( exchangeRate == null ) {
            return null;
        }
        UUID id = exchangeRate.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
