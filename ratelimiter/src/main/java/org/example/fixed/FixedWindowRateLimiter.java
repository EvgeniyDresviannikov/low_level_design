package org.example.fixed;


import org.example.RateLimiter;
import org.example.time.TimestampProvider;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FixedWindowRateLimiter implements RateLimiter {

    private AtomicLong lastRequestTime;
    private int timeWindowInSeconds;
    private int capacity;
    private AtomicInteger counter;
    private TimestampProvider timestampProvider;


    public FixedWindowRateLimiter(int timeWindowInSeconds, int capacity, TimestampProvider timestampProvider) {
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.capacity = capacity;
        this.counter = new AtomicInteger(capacity);
        this.lastRequestTime = new AtomicLong(0);
        this.timestampProvider = timestampProvider;
    }

    @Override
    public boolean isRequestAllowed() {
        long currentTime = timestampProvider.getNowTimestamp();

        updateCounter(currentTime);

        if (counter.get() > 0) {
            counter.decrementAndGet();
            lastRequestTime.compareAndSet(lastRequestTime.longValue(), currentTime);
            return true;
        }

        return false;
    }

    private void updateCounter(long currentTime) {
        if ( (currentTime - lastRequestTime.longValue()) / 1000 >= timeWindowInSeconds) {
            counter = new AtomicInteger(capacity);
        }
    }
}
