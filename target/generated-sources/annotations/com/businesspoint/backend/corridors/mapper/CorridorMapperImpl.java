package com.businesspoint.backend.corridors.mapper;

import com.businesspoint.backend.corridors.dto.CorridorRequest;
import com.businesspoint.backend.corridors.dto.CorridorResponse;
import com.businesspoint.backend.corridors.entity.Corridor;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class CorridorMapperImpl implements CorridorMapper {

    @Override
    public CorridorResponse toResponse(Corridor entity) {
        if ( entity == null ) {
            return null;
        }

        CorridorResponse corridorResponse = new CorridorResponse();

        corridorResponse.setId( entity.getId() );
        corridorResponse.setFromCountry( entity.getFromCountry() );
        corridorResponse.setToCountry( entity.getToCountry() );
        corridorResponse.setFromCurrency( entity.getFromCurrency() );
        corridorResponse.setToCurrency( entity.getToCurrency() );
        corridorResponse.setDeliveryMethod( entity.getDeliveryMethod() );
        corridorResponse.setDeliveryEstimate( entity.getDeliveryEstimate() );
        corridorResponse.setMinAmount( entity.getMinAmount() );
        corridorResponse.setMaxAmount( entity.getMaxAmount() );
        corridorResponse.setIsActive( entity.getIsActive() );
        corridorResponse.setCreatedAt( entity.getCreatedAt() );
        corridorResponse.setUpdatedAt( entity.getUpdatedAt() );

        return corridorResponse;
    }

    @Override
    public Corridor toEntity(CorridorRequest request) {
        if ( request == null ) {
            return null;
        }

        Corridor.CorridorBuilder corridor = Corridor.builder();

        corridor.fromCountry( request.getFromCountry() );
        corridor.toCountry( request.getToCountry() );
        corridor.fromCurrency( request.getFromCurrency() );
        corridor.toCurrency( request.getToCurrency() );
        corridor.deliveryMethod( request.getDeliveryMethod() );
        corridor.deliveryEstimate( request.getDeliveryEstimate() );
        corridor.minAmount( request.getMinAmount() );
        corridor.maxAmount( request.getMaxAmount() );
        corridor.isActive( request.getIsActive() );

        return corridor.build();
    }

    @Override
    public void updateEntity(Corridor corridor, CorridorRequest request) {
        if ( request == null ) {
            return;
        }

        corridor.setFromCountry( request.getFromCountry() );
        corridor.setToCountry( request.getToCountry() );
        corridor.setFromCurrency( request.getFromCurrency() );
        corridor.setToCurrency( request.getToCurrency() );
        corridor.setDeliveryMethod( request.getDeliveryMethod() );
        corridor.setDeliveryEstimate( request.getDeliveryEstimate() );
        corridor.setMinAmount( request.getMinAmount() );
        corridor.setMaxAmount( request.getMaxAmount() );
        corridor.setIsActive( request.getIsActive() );
    }
}
