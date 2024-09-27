package org.example.sliding.window;

import org.example.time.TimestampProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SlidingWindowRateLimiterTest {

    private final TimestampProvider timestampProvider = Mockito.mock(TimestampProvider.class);

    @Test
    void isRequestAllowed_false_after_exceeding_capacity() {
        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(1, 2, new TimestampProvider());

        assertTrue(slidingWindowRateLimiter.isRequestAllowed());
        assertTrue(slidingWindowRateLimiter.isRequestAllowed());

        assertFalse(slidingWindowRateLimiter.isRequestAllowed());
    }

    @Test
    void isRequestAllowed_true_after_exceeding_capacity() {
        long now = System.currentTimeMillis();
        when(timestampProvider.getNowTimestamp()).thenReturn(now);

        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(1, 2, timestampProvider);
        assertTrue(slidingWindowRateLimiter.isRequestAllowed());
        assertTrue(slidingWindowRateLimiter.isRequestAllowed());
        assertFalse(slidingWindowRateLimiter.isRequestAllowed());

        when(timestampProvider.getNowTimestamp()).thenReturn(now + 1000);
        assertTrue(slidingWindowRateLimiter.isRequestAllowed());
        assertFalse(slidingWindowRateLimiter.isRequestAllowed());
    }
}