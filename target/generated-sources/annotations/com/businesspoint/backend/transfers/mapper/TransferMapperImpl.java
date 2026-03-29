package com.businesspoint.backend.transfers.mapper;

import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.quotes.entity.Quote;
import com.businesspoint.backend.recipients.entity.Recipient;
import com.businesspoint.backend.transfers.dto.TransferResponse;
import com.businesspoint.backend.transfers.entity.Transfer;
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
public class TransferMapperImpl implements TransferMapper {

    @Override
    public TransferResponse toResponse(Transfer entity) {
        if ( entity == null ) {
            return null;
        }

        TransferResponse transferResponse = new TransferResponse();

        transferResponse.setUserId( entityUserId( entity ) );
        transferResponse.setQuoteId( entityQuoteId( entity ) );
        transferResponse.setRecipientId( entityRecipientId( entity ) );
        transferResponse.setCorridorId( entityCorridorId( entity ) );
        transferResponse.setId( entity.getId() );
        transferResponse.setAmountSent( entity.getAmountSent() );
        transferResponse.setAmountReceived( entity.getAmountReceived() );
        transferResponse.setFee( entity.getFee() );
        transferResponse.setTotalPaid( entity.getTotalPaid() );
        transferResponse.setSendCurrency( entity.getSendCurrency() );
        transferResponse.setReceiveCurrency( entity.getReceiveCurrency() );
        transferResponse.setTransferReason( entity.getTransferReason() );
        transferResponse.setStatus( entity.getStatus() );
        transferResponse.setReferenceCode( entity.getReferenceCode() );
        transferResponse.setReceiptUrl( entity.getReceiptUrl() );
        transferResponse.setCompletedAt( entity.getCompletedAt() );
        transferResponse.setCreatedAt( entity.getCreatedAt() );

        return transferResponse;
    }

    private UUID entityUserId(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }
        User user = transfer.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityQuoteId(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }
        Quote quote = transfer.getQuote();
        if ( quote == null ) {
            return null;
        }
        UUID id = quote.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityRecipientId(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }
        Recipient recipient = transfer.getRecipient();
        if ( recipient == null ) {
            return null;
        }
        UUID id = recipient.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityCorridorId(Transfer transfer) {
        if ( transfer == null ) {
            return null;
        }
        Corridor corridor = transfer.getCorridor();
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
