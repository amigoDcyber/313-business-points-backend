package com.businesspoint.backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final StringRedisTemplate redisTemplate;

    public void blacklistToken(String token, long expirationMs) {
        redisTemplate.opsForValue().set(getRedisKey(token), "blacklisted", expirationMs, TimeUnit.MILLISECONDS);
    }

    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(getRedisKey(token)));
    }
    
    private String getRedisKey(String token) {
        return "jwt:blacklist:" + token;
    }
}
