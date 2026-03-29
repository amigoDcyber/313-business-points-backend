package com.businesspoint.backend.devices.entity;

import com.businesspoint.backend.common.BaseEntity;
import com.businesspoint.backend.users.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Device extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "platform")
    private String platform;

    @Column(name = "is_trusted")
    private Boolean isTrusted;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;
}
