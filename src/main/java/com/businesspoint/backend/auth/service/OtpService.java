package com.businesspoint.backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final StringRedisTemplate redisTemplate;
    private final Random random = new Random();

    public String generateOtp(String identifier) {
        String otp = String.format("%06d", random.nextInt(999999));
        String redisKey = "otp:" + identifier;
        redisTemplate.opsForValue().set(redisKey, otp, Duration.ofMinutes(5));
        return otp;
    }

    public boolean verifyOtp(String identifier, String otp) {
        String redisKey = "otp:" + identifier;
        String storedOtp = redisTemplate.opsForValue().get(redisKey);
        
        if (storedOtp != null && storedOtp.equals(otp)) {
            redisTemplate.delete(redisKey);
            return true;
        }
        return false;
    }
}
