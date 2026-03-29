package com.businesspoint.backend.recipients.mapper;

import com.businesspoint.backend.recipients.dto.RecipientRequest;
import com.businesspoint.backend.recipients.dto.RecipientResponse;
import com.businesspoint.backend.recipients.entity.Recipient;
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
public class RecipientMapperImpl implements RecipientMapper {

    @Override
    public RecipientResponse toResponse(Recipient entity) {
        if ( entity == null ) {
            return null;
        }

        RecipientResponse recipientResponse = new RecipientResponse();

        recipientResponse.setUserId( entityUserId( entity ) );
        recipientResponse.setId( entity.getId() );
        recipientResponse.setFullName( entity.getFullName() );
        recipientResponse.setPhone( entity.getPhone() );
        recipientResponse.setCountry( entity.getCountry() );
        recipientResponse.setDeliveryMethod( entity.getDeliveryMethod() );
        recipientResponse.setBankName( entity.getBankName() );
        recipientResponse.setAccountNumber( entity.getAccountNumber() );
        recipientResponse.setWalletType( entity.getWalletType() );
        recipientResponse.setNetworkOperator( entity.getNetworkOperator() );
        recipientResponse.setIsFavorite( entity.getIsFavorite() );
        recipientResponse.setCreatedAt( entity.getCreatedAt() );
        recipientResponse.setUpdatedAt( entity.getUpdatedAt() );

        return recipientResponse;
    }

    @Override
    public Recipient toEntity(RecipientRequest request) {
        if ( request == null ) {
            return null;
        }

        Recipient.RecipientBuilder recipient = Recipient.builder();

        recipient.fullName( request.getFullName() );
        recipient.phone( request.getPhone() );
        recipient.country( request.getCountry() );
        recipient.deliveryMethod( request.getDeliveryMethod() );
        recipient.bankName( request.getBankName() );
        recipient.accountNumber( request.getAccountNumber() );
        recipient.walletType( request.getWalletType() );
        recipient.networkOperator( request.getNetworkOperator() );
        recipient.isFavorite( request.getIsFavorite() );

        return recipient.build();
    }

    @Override
    public void updateEntity(Recipient recipient, RecipientRequest request) {
        if ( request == null ) {
            return;
        }

        recipient.setFullName( request.getFullName() );
        recipient.setPhone( request.getPhone() );
        recipient.setCountry( request.getCountry() );
        recipient.setDeliveryMethod( request.getDeliveryMethod() );
        recipient.setBankName( request.getBankName() );
        recipient.setAccountNumber( request.getAccountNumber() );
        recipient.setWalletType( request.getWalletType() );
        recipient.setNetworkOperator( request.getNetworkOperator() );
        recipient.setIsFavorite( request.getIsFavorite() );
    }

    private UUID entityUserId(Recipient recipient) {
        if ( recipient == null ) {
            return null;
        }
        User user = recipient.getUser();
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
