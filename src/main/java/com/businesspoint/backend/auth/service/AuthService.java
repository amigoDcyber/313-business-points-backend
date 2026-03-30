package com.businesspoint.backend.auth.service;

import com.businesspoint.backend.auth.dto.AuthResponse;
import com.businesspoint.backend.auth.dto.LoginRequest;
import com.businesspoint.backend.auth.dto.SignupRequest;
import com.businesspoint.backend.auth.jwt.JwtService;
import com.businesspoint.backend.auth.security.CustomUserDetails;
import com.businesspoint.backend.common.enums.Role;
import com.businesspoint.backend.common.exception.BusinessException;
import com.businesspoint.backend.devices.entity.Device;
import com.businesspoint.backend.devices.repository.DeviceRepository;
import com.businesspoint.backend.users.dto.UserResponse;
import com.businesspoint.backend.users.entity.User;
import com.businesspoint.backend.users.mapper.UserMapper;
import com.businesspoint.backend.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Transactional
    public AuthResponse register(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already registered");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .status(Role.ROLE_USER)
                .referralCode(request.getReferralCode())
                .build();

        userRepository.save(user);

        return buildAuthResponse(user, false);
    }

    @Transactional
    public AuthResponse authenticate(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("User not found"));

        boolean requiresOtp = checkDeviceTrust(user, request.getDeviceId());

        return buildAuthResponse(user, requiresOtp);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserResponse(java.util.UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException("User not found"));
        return userMapper.toResponse(user);
    }

    private boolean checkDeviceTrust(User user, String deviceId) {
        if (deviceId == null) return true; // Force OTP if no device ID is provided

        return deviceRepository.findByUserIdAndDeviceId(user.getId(), deviceId)
                .map(device -> {
                    device.setLastSeen(LocalDateTime.now());
                    deviceRepository.save(device);
                    return !Boolean.TRUE.equals(device.getIsTrusted());
                })
                .orElseGet(() -> {
                    Device newDevice = Device.builder()
                            .user(user)
                            .deviceId(deviceId)
                            .isTrusted(false)
                            .lastSeen(LocalDateTime.now())
                            .build();
                    deviceRepository.save(newDevice);
                    return true;
                });
    }

    private AuthResponse buildAuthResponse(User user, boolean requiresOtp) {
        String jwtToken = jwtService.generateToken(new CustomUserDetails(user));
        UserResponse userResponse = userMapper.toResponse(user);
        return AuthResponse.builder()
                .accessToken(jwtToken)
                .user(userResponse)
                .requiresOtp(requiresOtp)
                .build();
    }
}
