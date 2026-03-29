package com.businesspoint.backend.transfers.mapper;

import com.businesspoint.backend.transfers.dto.TransferRequest;
import com.businesspoint.backend.transfers.dto.TransferResponse;
import com.businesspoint.backend.transfers.entity.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "quote.id", target = "quoteId")
    @Mapping(source = "recipient.id", target = "recipientId")
    @Mapping(source = "corridor.id", target = "corridorId")
    TransferResponse toResponse(Transfer entity);
}
