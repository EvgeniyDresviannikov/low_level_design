package org.example;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class HitCounter {

    int windowInSeconds;
    Queue<Long> hits;

    public HitCounter(int windowInSeconds) {
        this.windowInSeconds = windowInSeconds;
        this.hits = new ConcurrentLinkedQueue<>();
    }

    public void hit(long timestamp) {
        hits.add(timestamp);
    }

    public int getHits(long timestamp) {
        long target = timestamp - windowInSeconds * 1000L;

        long peek = hits.peek();
        while (peek < target) {
            hits.poll();
            if (hits.isEmpty()) break;
            peek = hits.peek();
        }


        return hits.size();
    }
}
