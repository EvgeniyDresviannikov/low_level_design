package org.example.fixed;

import org.example.time.TimestampProvider;

import java.util.HashMap;
import java.util.Map;

public class FixedRateLimiterUserCreator {

    private static final int DEFAULT_CAPACITY = 10;
    private static final int DEFAULT_WINDOW_SIZE_IN_SECS = 1;

    private final Map<String, FixedWindowRateLimiter> userRateLimiterMap;

    public FixedRateLimiterUserCreator() {
        this.userRateLimiterMap = new HashMap<>();
    }

    public boolean accessApplication(String userId) {
        FixedWindowRateLimiter userRateLimiter;
        if (userRateLimiterMap.containsKey(userId)) {
            userRateLimiter = userRateLimiterMap.get(userId);
        } else {
            userRateLimiter = new FixedWindowRateLimiter(DEFAULT_WINDOW_SIZE_IN_SECS, DEFAULT_CAPACITY, new TimestampProvider());
            userRateLimiterMap.put(userId, userRateLimiter);
        }

        return userRateLimiter.isRequestAllowed();
    }

    public void setConfigForUser(String userId, int capacity, int windowSize) {
        FixedWindowRateLimiter rateLimiter = new FixedWindowRateLimiter(capacity, windowSize, new TimestampProvider());
        userRateLimiterMap.put(userId, rateLimiter);
    }


}
