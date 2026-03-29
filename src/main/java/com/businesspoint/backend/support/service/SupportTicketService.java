package com.businesspoint.backend.support.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.support.dto.SupportTicketRequest;
import com.businesspoint.backend.support.dto.SupportTicketResponse;
import com.businesspoint.backend.support.entity.SupportTicket;
import com.businesspoint.backend.support.mapper.SupportTicketMapper;
import com.businesspoint.backend.support.repository.SupportTicketRepository;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.transfers.repository.TransferRepository;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportTicketService {

    private final SupportTicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final SupportTicketMapper ticketMapper;

    @Transactional(readOnly = true)
    public List<SupportTicketResponse> getUserTickets(UUID userId) {
        return ticketRepository.findAllByUserId(userId).stream()
                .map(ticketMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public SupportTicketResponse createTicket(UUID userId, SupportTicketRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = null;
        if (request.getTransferId() != null) {
            transfer = transferRepository.findById(request.getTransferId()).orElse(null);
        }

        SupportTicket ticket = ticketMapper.toEntity(request);
        ticket.setUser(user);
        ticket.setTransfer(transfer);
        ticket.setStatus("OPEN");
        
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }

    @Transactional
    public SupportTicketResponse resolveTicket(UUID ticketId) {
        SupportTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
                
        ticket.setStatus("RESOLVED");
        ticket.setResolvedAt(LocalDateTime.now());
        
        return ticketMapper.toResponse(ticketRepository.save(ticket));
    }
}
