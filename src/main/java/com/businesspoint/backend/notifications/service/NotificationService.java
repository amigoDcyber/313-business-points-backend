package com.businesspoint.backend.notifications.service;

import com.businesspoint.backend.common.exception.ResourceNotFoundException;
import com.businesspoint.backend.notifications.dto.NotificationRequest;
import com.businesspoint.backend.notifications.dto.NotificationResponse;
import com.businesspoint.backend.notifications.entity.Notification;
import com.businesspoint.backend.notifications.mapper.NotificationMapper;
import com.businesspoint.backend.notifications.repository.NotificationRepository;
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
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;
    private final NotificationMapper notificationMapper;

    @Transactional(readOnly = true)
    public List<NotificationResponse> getUserNotifications(UUID userId) {
        return notificationRepository.findAllByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(notificationMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public NotificationResponse sendNotification(UUID userId, NotificationRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Transfer transfer = null;
        if (request.getTransferId() != null) {
            transfer = transferRepository.findById(request.getTransferId()).orElse(null);
        }

        Notification notification = notificationMapper.toEntity(request);
        notification.setUser(user);
        notification.setTransfer(transfer);
        notification.setIsRead(false);
        notification.setSentAt(LocalDateTime.now());
        
        // Simulating immediate dispatch via SendGrid or Push Provider in the background
        
        return notificationMapper.toResponse(notificationRepository.save(notification));
    }

    @Transactional
    public void markAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
