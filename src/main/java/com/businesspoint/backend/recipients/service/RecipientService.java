package com.businesspoint.backend.recipients.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.recipients.dto.RecipientRequest;
import com.businesspoint.backend.recipients.dto.RecipientResponse;
import com.businesspoint.backend.recipients.entity.Recipient;
import com.businesspoint.backend.recipients.mapper.RecipientMapper;
import com.businesspoint.backend.recipients.repository.RecipientRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientService {

    private final RecipientRepository recipientRepository;
    private final UserRepository userRepository;
    private final RecipientMapper recipientMapper;

    @Transactional(readOnly = true)
    public List<RecipientResponse> getUserRecipients(UUID userId) {
        return recipientRepository.findAllByUserId(userId).stream()
                .map(recipientMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public RecipientResponse createRecipient(UUID userId, RecipientRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Recipient recipient = recipientMapper.toEntity(request);
        recipient.setUser(user);
        
        return recipientMapper.toResponse(recipientRepository.save(recipient));
    }

    @Transactional
    public RecipientResponse updateRecipient(UUID recipientId, RecipientRequest request) {
        Recipient recipient = recipientRepository.findById(recipientId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipient not found"));
                
        recipientMapper.updateEntity(recipient, request);
        return recipientMapper.toResponse(recipientRepository.save(recipient));
    }
}
