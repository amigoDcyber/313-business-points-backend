package com.businesspoint.backend.corridors.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.corridors.dto.CorridorRequest;
import com.businesspoint.backend.corridors.dto.CorridorResponse;
import com.businesspoint.backend.corridors.entity.Corridor;
import com.businesspoint.backend.corridors.mapper.CorridorMapper;
import com.businesspoint.backend.corridors.repository.CorridorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CorridorService {

    private final CorridorRepository corridorRepository;
    private final CorridorMapper corridorMapper;

    @Transactional(readOnly = true)
    public List<CorridorResponse> getActiveCorridors() {
        return corridorRepository.findByIsActiveTrue().stream()
                .map(corridorMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CorridorResponse getCorridorById(UUID id) {
        return corridorRepository.findById(id)
                .map(corridorMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found"));
    }

    @Transactional
    public CorridorResponse createCorridor(CorridorRequest request) {
        Corridor corridor = corridorMapper.toEntity(request);
        return corridorMapper.toResponse(corridorRepository.save(corridor));
    }

    @Transactional
    public CorridorResponse updateCorridor(UUID id, CorridorRequest request) {
        Corridor corridor = corridorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Corridor not found"));
                
        corridorMapper.updateEntity(corridor, request);
        return corridorMapper.toResponse(corridorRepository.save(corridor));
    }
}
