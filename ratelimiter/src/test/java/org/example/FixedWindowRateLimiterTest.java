package org.example;

import org.example.fixed.FixedWindowRateLimiter;
import org.example.time.TimestampProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FixedWindowRateLimiterTest {

    private final TimestampProvider timestampProvider = Mockito.mock(TimestampProvider.class);

    @Test
    void isRequestAllowed_false_after_exceeding_capacity() {
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(1, 2, new TimestampProvider());

        assertTrue(fixedWindowRateLimiter.isRequestAllowed());
        assertTrue(fixedWindowRateLimiter.isRequestAllowed());

        assertFalse(fixedWindowRateLimiter.isRequestAllowed());
    }

    @Test
    void isRequestAllowed_true_after_exceeding_capacity() {
        long now = System.currentTimeMillis();
        when(timestampProvider.getNowTimestamp()).thenReturn(now);

        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(1, 2, timestampProvider);
        assertTrue(fixedWindowRateLimiter.isRequestAllowed());
        assertTrue(fixedWindowRateLimiter.isRequestAllowed());

        when(timestampProvider.getNowTimestamp()).thenReturn(now+1000);
        assertTrue(fixedWindowRateLimiter.isRequestAllowed());
    }
}