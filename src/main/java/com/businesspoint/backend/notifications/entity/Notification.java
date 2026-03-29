package com.businesspoint.backend.notifications.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String channel;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "is_read")
    private Boolean isRead;

    @Column(name = "sent_at")
    private LocalDateTime sentAt;
}
