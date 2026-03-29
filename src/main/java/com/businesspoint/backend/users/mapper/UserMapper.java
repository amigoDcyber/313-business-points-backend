package com.businesspoint.backend.users.mapper;

import com.businesspoint.backend.users.dto.UserRequest;
import com.businesspoint.backend.users.dto.UserResponse;
import com.businesspoint.backend.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User entity);
    User toEntity(UserRequest request);
    void updateEntity(@MappingTarget User user, UserRequest request);
}
