package org.example.sliding.window;

import org.example.RateLimiter;
import org.example.time.TimestampProvider;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindowRateLimiter implements RateLimiter {

    private final Queue<Long> slidingWindow;
    int timeWindowInSeconds;
    int capacity;
    private final TimestampProvider timestampProvider;

    public SlidingWindowRateLimiter(int timeWindowInSeconds, int capacity, TimestampProvider timestampProvider) {
        this.slidingWindow = new ConcurrentLinkedQueue<>();
        this.timeWindowInSeconds = timeWindowInSeconds;
        this.capacity = capacity;
        this.timestampProvider = timestampProvider;
    }

    @Override
    public boolean isRequestAllowed() {
        long now = timestampProvider.getNowTimestamp();

        updateSlidingWindow(now);

        if (slidingWindow.size() < capacity) {
            slidingWindow.add(now);
            return true;
        }
        return false;
    }

    private void updateSlidingWindow(long timeStampNow) {
        if (slidingWindow.isEmpty()) return;

        long calculatedTime = (timeStampNow - slidingWindow.peek()) / 1000;
        while (calculatedTime >= timeWindowInSeconds) {
            System.out.println("going to poll");
            slidingWindow.poll();
            if (slidingWindow.isEmpty()) break;
            calculatedTime = (timeStampNow - slidingWindow.peek()) / 1000;
        }
    }
}
