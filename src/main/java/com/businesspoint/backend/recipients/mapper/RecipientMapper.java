package com.businesspoint.backend.recipients.mapper;

import com.businesspoint.backend.recipients.dto.RecipientRequest;
import com.businesspoint.backend.recipients.dto.RecipientResponse;
import com.businesspoint.backend.recipients.entity.Recipient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RecipientMapper {
    @Mapping(source = "user.id", target = "userId")
    RecipientResponse toResponse(Recipient entity);
    
    @Mapping(target = "user", ignore = true)
    Recipient toEntity(RecipientRequest request);
    
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget Recipient recipient, RecipientRequest request);
}
