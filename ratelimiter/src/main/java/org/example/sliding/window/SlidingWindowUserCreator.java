package org.example.sliding.window;

import org.example.time.TimestampProvider;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowUserCreator {
    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_WINDOW_SIZE_IN_SECS = 1;

    private final Map<String, SlidingWindowRateLimiter> userRateLimiterMap;

    public SlidingWindowUserCreator() {
        this.userRateLimiterMap = new HashMap<>();
    }

    public boolean accessApplication(String userId) {
        SlidingWindowRateLimiter userRateLimiter;
        if (userRateLimiterMap.containsKey(userId)) {
            userRateLimiter = userRateLimiterMap.get(userId);
        } else {
            userRateLimiter = new SlidingWindowRateLimiter(DEFAULT_WINDOW_SIZE_IN_SECS, DEFAULT_CAPACITY, new TimestampProvider());
            userRateLimiterMap.put(userId, userRateLimiter);
        }

        return userRateLimiter.isRequestAllowed();
    }

    public void setConfigForUser(String userId, int windowSize, int capacity) {
        SlidingWindowRateLimiter rateLimiter = new SlidingWindowRateLimiter(windowSize, capacity, new TimestampProvider());
        userRateLimiterMap.put(userId, rateLimiter);
    }
}
