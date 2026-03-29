package com.businesspoint.backend.support.mapper;

import com.businesspoint.backend.support.dto.SupportTicketRequest;
import com.businesspoint.backend.support.dto.SupportTicketResponse;
import com.businesspoint.backend.support.entity.SupportTicket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupportTicketMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "transfer.id", target = "transferId")
    SupportTicketResponse toResponse(SupportTicket entity);
    
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "transfer", ignore = true)
    SupportTicket toEntity(SupportTicketRequest request);
}
