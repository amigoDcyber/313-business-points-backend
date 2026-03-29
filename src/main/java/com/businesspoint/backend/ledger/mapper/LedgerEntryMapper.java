package com.businesspoint.backend.ledger.mapper;

import com.businesspoint.backend.ledger.dto.LedgerEntryRequest;
import com.businesspoint.backend.ledger.dto.LedgerEntryResponse;
import com.businesspoint.backend.ledger.entity.LedgerEntry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LedgerEntryMapper {
    @Mapping(source = "transfer.id", target = "transferId")
    LedgerEntryResponse toResponse(LedgerEntry entity);
    
    @Mapping(target = "transfer", ignore = true)
    LedgerEntry toEntity(LedgerEntryRequest request);
}
