package com.businesspoint.backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    public boolean isAllowed(String key, int limit, Duration window) {
        String redisKey = "rate_limit:" + key;
        Long count = redisTemplate.opsForValue().increment(redisKey);
        
        if (count == null) {
            return false;
        }
        
        if (count == 1) {
            redisTemplate.expire(redisKey, window);
        }
        
        return count <= limit;
    }
}
