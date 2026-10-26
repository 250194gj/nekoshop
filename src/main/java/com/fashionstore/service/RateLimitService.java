package com.fashionstore.service;

import io.github.bucket4j.Bucket;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimitService {
    private final ConcurrentMap<String, Bucket> buckets = new ConcurrentHashMap<>();
    private Bucket createBucket() {
        return Bucket.builder()
                .addLimit(limit -> limit
                        .capacity(10)
                        .refillGreedy(1, Duration.ofMinutes(2)))
                .build();
    }
    private Bucket getBucket(String ip) {
        return buckets.computeIfAbsent(ip, key -> createBucket());
    }
    public boolean allowRequest(String ip) {
        Bucket bucket = getBucket(ip);
        return bucket.tryConsume(1);
    }
}
