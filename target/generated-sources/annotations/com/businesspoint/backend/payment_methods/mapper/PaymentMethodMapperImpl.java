package com.businesspoint.backend.payment_methods.mapper;

import com.businesspoint.backend.payment_methods.dto.PaymentMethodRequest;
import com.businesspoint.backend.payment_methods.dto.PaymentMethodResponse;
import com.businesspoint.backend.payment_methods.entity.PaymentMethod;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class PaymentMethodMapperImpl implements PaymentMethodMapper {

    @Override
    public PaymentMethodResponse toResponse(PaymentMethod entity) {
        if ( entity == null ) {
            return null;
        }

        PaymentMethodResponse paymentMethodResponse = new PaymentMethodResponse();

        paymentMethodResponse.setUserId( entityUserId( entity ) );
        paymentMethodResponse.setId( entity.getId() );
        paymentMethodResponse.setType( entity.getType() );
        paymentMethodResponse.setProviderToken( entity.getProviderToken() );
        paymentMethodResponse.setCardLast4( entity.getCardLast4() );
        paymentMethodResponse.setCardBrand( entity.getCardBrand() );
        paymentMethodResponse.setBankName( entity.getBankName() );
        paymentMethodResponse.setIsDefault( entity.getIsDefault() );
        paymentMethodResponse.setCreatedAt( entity.getCreatedAt() );
        paymentMethodResponse.setUpdatedAt( entity.getUpdatedAt() );

        return paymentMethodResponse;
    }

    @Override
    public PaymentMethod toEntity(PaymentMethodRequest request) {
        if ( request == null ) {
            return null;
        }

        PaymentMethod.PaymentMethodBuilder paymentMethod = PaymentMethod.builder();

        paymentMethod.type( request.getType() );
        paymentMethod.providerToken( request.getProviderToken() );
        paymentMethod.cardLast4( request.getCardLast4() );
        paymentMethod.cardBrand( request.getCardBrand() );
        paymentMethod.bankName( request.getBankName() );
        paymentMethod.isDefault( request.getIsDefault() );

        return paymentMethod.build();
    }

    @Override
    public void updateEntity(PaymentMethod paymentMethod, PaymentMethodRequest request) {
        if ( request == null ) {
            return;
        }

        paymentMethod.setType( request.getType() );
        paymentMethod.setProviderToken( request.getProviderToken() );
        paymentMethod.setCardLast4( request.getCardLast4() );
        paymentMethod.setCardBrand( request.getCardBrand() );
        paymentMethod.setBankName( request.getBankName() );
        paymentMethod.setIsDefault( request.getIsDefault() );
    }

    private UUID entityUserId(PaymentMethod paymentMethod) {
        if ( paymentMethod == null ) {
            return null;
        }
        User user = paymentMethod.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
