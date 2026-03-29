package com.businesspoint.backend.users.mapper;

import com.businesspoint.backend.users.dto.UserRequest;
import com.businesspoint.backend.users.dto.UserResponse;
import com.businesspoint.backend.users.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-29T23:43:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Arch Linux)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toResponse(User entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( entity.getId() );
        userResponse.setFirstName( entity.getFirstName() );
        userResponse.setLastName( entity.getLastName() );
        userResponse.setEmail( entity.getEmail() );
        userResponse.setPhone( entity.getPhone() );
        userResponse.setStatus( entity.getStatus() );
        userResponse.setReferralCode( entity.getReferralCode() );
        userResponse.setLanguage( entity.getLanguage() );
        userResponse.setCreatedAt( entity.getCreatedAt() );
        userResponse.setUpdatedAt( entity.getUpdatedAt() );

        return userResponse;
    }

    @Override
    public User toEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.email( request.getEmail() );
        user.phone( request.getPhone() );
        user.language( request.getLanguage() );

        return user.build();
    }

    @Override
    public void updateEntity(User user, UserRequest request) {
        if ( request == null ) {
            return;
        }

        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setEmail( request.getEmail() );
        user.setPhone( request.getPhone() );
        user.setLanguage( request.getLanguage() );
    }
}
