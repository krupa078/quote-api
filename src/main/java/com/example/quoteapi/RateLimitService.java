package com.example.quoteapi.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    private final long capacity;
    private final Duration refillDuration;

    public RateLimitService(@Value("${rate.limit.capacity:5}") long capacity,
                            @Value("${rate.limit.refill:1m}") String refill) {
        this.capacity = capacity;

        // Example: 1m → PT1M (ISO-8601 Duration)
        this.refillDuration = Duration.parse("PT" + refill.toUpperCase().replace(" ", ""));
    }

    public Bucket resolveBucket(String ip) {
        return cache.computeIfAbsent(ip, k -> {
            Refill refill = Refill.intervally(capacity, refillDuration);
            Bandwidth limit = Bandwidth.classic(capacity, refill);
            return Bucket.builder().addLimit(limit).build();
        });
    }
}
