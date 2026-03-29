package com.businesspoint.backend.transfers.mapper;

import com.businesspoint.backend.transfers.dto.TransferStatusLogResponse;
import com.businesspoint.backend.transfers.entity.TransferStatusLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferStatusLogMapper {
    @Mapping(source = "transfer.id", target = "transferId")
    TransferStatusLogResponse toResponse(TransferStatusLog entity);
}
