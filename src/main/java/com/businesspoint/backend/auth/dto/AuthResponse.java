package com.businesspoint.backend.auth.dto;

import com.businesspoint.backend.users.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private UserResponse user;
    private boolean requiresOtp;
}
