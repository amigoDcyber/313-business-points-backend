package com.businesspoint.backend.corridors.mapper;

import com.businesspoint.backend.corridors.dto.CorridorRequest;
import com.businesspoint.backend.corridors.dto.CorridorResponse;
import com.businesspoint.backend.corridors.entity.Corridor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CorridorMapper {
    CorridorResponse toResponse(Corridor entity);
    Corridor toEntity(CorridorRequest request);
    void updateEntity(@MappingTarget Corridor corridor, CorridorRequest request);
}
