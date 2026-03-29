package com.businesspoint.backend.transfers.mapper;

import com.businesspoint.backend.transfers.dto.TransferStatusLogResponse;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.entity.TransferStatusLog;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class TransferStatusLogMapperImpl implements TransferStatusLogMapper {

    @Override
    public TransferStatusLogResponse toResponse(TransferStatusLog entity) {
        if ( entity == null ) {
            return null;
        }

        TransferStatusLogResponse transferStatusLogResponse = new TransferStatusLogResponse();

        transferStatusLogResponse.setTransferId( entityTransferId( entity ) );
        transferStatusLogResponse.setId( entity.getId() );
        transferStatusLogResponse.setStatus( entity.getStatus() );
        transferStatusLogResponse.setNote( entity.getNote() );
        transferStatusLogResponse.setCreatedAt( entity.getCreatedAt() );

        return transferStatusLogResponse;
    }

    private UUID entityTransferId(TransferStatusLog transferStatusLog) {
        if ( transferStatusLog == null ) {
            return null;
        }
        Transfer transfer = transferStatusLog.getTransfer();
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
