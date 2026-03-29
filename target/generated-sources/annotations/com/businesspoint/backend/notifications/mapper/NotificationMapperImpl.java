package com.businesspoint.backend.notifications.mapper;

import com.businesspoint.backend.notifications.dto.NotificationRequest;
import com.businesspoint.backend.notifications.dto.NotificationResponse;
import com.businesspoint.backend.notifications.entity.Notification;
import com.businesspoint.backend.transfers.entity.Transfer;
import com.businesspoint.backend.users.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public NotificationResponse toResponse(Notification entity) {
        if ( entity == null ) {
            return null;
        }

        NotificationResponse notificationResponse = new NotificationResponse();

        notificationResponse.setUserId( entityUserId( entity ) );
        notificationResponse.setTransferId( entityTransferId( entity ) );
        notificationResponse.setId( entity.getId() );
        notificationResponse.setType( entity.getType() );
        notificationResponse.setChannel( entity.getChannel() );
        notificationResponse.setTitle( entity.getTitle() );
        notificationResponse.setMessage( entity.getMessage() );
        notificationResponse.setIsRead( entity.getIsRead() );
        notificationResponse.setSentAt( entity.getSentAt() );
        notificationResponse.setCreatedAt( entity.getCreatedAt() );

        return notificationResponse;
    }

    @Override
    public Notification toEntity(NotificationRequest request) {
        if ( request == null ) {
            return null;
        }

        Notification.NotificationBuilder notification = Notification.builder();

        notification.type( request.getType() );
        notification.channel( request.getChannel() );
        notification.title( request.getTitle() );
        notification.message( request.getMessage() );

        return notification.build();
    }

    private UUID entityUserId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        User user = notification.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID entityTransferId(Notification notification) {
        if ( notification == null ) {
            return null;
        }
        Transfer transfer = notification.getTransfer();
        if ( transfer == null ) {
            return null;
        }
        UUID id = transfer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
