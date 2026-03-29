package com.businesspoint.backend.ledger.mapper;

import com.businesspoint.backend.ledger.dto.LedgerEntryRequest;
import com.businesspoint.backend.ledger.dto.LedgerEntryResponse;
import com.businesspoint.backend.ledger.entity.LedgerEntry;
import com.businesspoint.backend.transfers.entity.Transfer;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class LedgerEntryMapperImpl implements LedgerEntryMapper {

    @Override
    public LedgerEntryResponse toResponse(LedgerEntry entity) {
        if ( entity == null ) {
            return null;
        }

        LedgerEntryResponse ledgerEntryResponse = new LedgerEntryResponse();

        ledgerEntryResponse.setTransferId( entityTransferId( entity ) );
        ledgerEntryResponse.setId( entity.getId() );
        ledgerEntryResponse.setEntryType( entity.getEntryType() );
        ledgerEntryResponse.setAccountDebit( entity.getAccountDebit() );
        ledgerEntryResponse.setAccountCredit( entity.getAccountCredit() );
        ledgerEntryResponse.setAmount( entity.getAmount() );
        ledgerEntryResponse.setCurrency( entity.getCurrency() );
        ledgerEntryResponse.setDescription( entity.getDescription() );
        ledgerEntryResponse.setCreatedAt( entity.getCreatedAt() );

        return ledgerEntryResponse;
    }

    @Override
    public LedgerEntry toEntity(LedgerEntryRequest request) {
        if ( request == null ) {
            return null;
        }

        LedgerEntry.LedgerEntryBuilder ledgerEntry = LedgerEntry.builder();

        ledgerEntry.entryType( request.getEntryType() );
        ledgerEntry.accountDebit( request.getAccountDebit() );
        ledgerEntry.accountCredit( request.getAccountCredit() );
        ledgerEntry.amount( request.getAmount() );
        ledgerEntry.currency( request.getCurrency() );
        ledgerEntry.description( request.getDescription() );

        return ledgerEntry.build();
    }

    private UUID entityTransferId(LedgerEntry ledgerEntry) {
        if ( ledgerEntry == null ) {
            return null;
        }
        Transfer transfer = ledgerEntry.getTransfer();
        if ( transfer == null ) {
            return null;
        }
        UUID id = transfer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
