package com.businesspoint.backend.support.mapper;

import com.businesspoint.backend.support.dto.SupportTicketRequest;
import com.businesspoint.backend.support.dto.SupportTicketResponse;
import com.businesspoint.backend.support.entity.SupportTicket;
import com.businesspoint.backend.transfers.entity.Transfer;
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
public class SupportTicketMapperImpl implements SupportTicketMapper {

    @Override
    public SupportTicketResponse toResponse(SupportTicket entity) {
        if ( entity == null ) {
            return null;
        }

        SupportTicketResponse supportTicketResponse = new SupportTicketResponse();

        supportTicketResponse.setUserId( entityUserId( entity ) );
        supportTicketResponse.setTransferId( entityTransferId( entity ) );
        supportTicketResponse.setId( entity.getId() );
        supportTicketResponse.setSubject( entity.getSubject() );
        supportTicketResponse.setStatus( entity.getStatus() );
        supportTicketResponse.setPriority( entity.getPriority() );
        supportTicketResponse.setResolvedAt( entity.getResolvedAt() );
        supportTicketResponse.setCreatedAt( entity.getCreatedAt() );
        supportTicketResponse.setUpdatedAt( entity.getUpdatedAt() );

        return supportTicketResponse;
    }

    @Override
    public SupportTicket toEntity(SupportTicketRequest request) {
        if ( request == null ) {
            return null;
        }

        SupportTicket.SupportTicketBuilder supportTicket = SupportTicket.builder();

        supportTicket.subject( request.getSubject() );
        supportTicket.priority( request.getPriority() );

        return supportTicket.build();
    }

    private UUID entityUserId(SupportTicket supportTicket) {
        if ( supportTicket == null ) {
            return null;
        }
        User user = supportTicket.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityTransferId(SupportTicket supportTicket) {
        if ( supportTicket == null ) {
            return null;
        }
        Transfer transfer = supportTicket.getTransfer();
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
