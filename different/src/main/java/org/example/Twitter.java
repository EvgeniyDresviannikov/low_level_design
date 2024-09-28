package org.example;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Twitter {

    Map<Integer, Set<Integer>> followMap;
    Map<Integer, List<Tweet>> tweetMap;
    AtomicLong counter;

    public Twitter() {
        followMap = new ConcurrentHashMap<>();
        tweetMap = new ConcurrentHashMap<>();
        counter = new AtomicLong(0L);

    }

    public void postTweet(int userId, int tweetId) {
        long nextCounter = counter.incrementAndGet();
        Tweet tweet = new Tweet(nextCounter, tweetId);
        if (tweetMap.containsKey(userId)) {
            tweetMap.get(userId).add(tweet);
        } else {
            List<Tweet> lst = new ArrayList<>();
            lst.add(tweet);
            tweetMap.put(userId, lst);
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        Queue<Tweet> maxHeap = new PriorityQueue<>((o1, o2) -> Math.toIntExact(o2.counter - o1.counter));

        Set<Integer> followeeIds = followMap.getOrDefault(userId, Collections.emptySet());
        for (int followeeId : followeeIds) {
            tweetMap.getOrDefault(followeeId, Collections.emptyList()).forEach(maxHeap::add);
        }

        tweetMap.getOrDefault(userId, Collections.emptyList()).forEach(maxHeap::add);

        List<Integer> res = new ArrayList<>();
        int n = 10;
        while (!maxHeap.isEmpty() && n > 0) {
            res.add(maxHeap.poll().tweetId);
            n--;
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).add(followeeId);
        } else {
            Set<Integer> set = new HashSet<>();
            set.add(followeeId);
            followMap.put(followerId, set);
        }
    }

    public void unfollow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) return;

        followMap.get(followerId).remove(followeeId);
    }
}

class Tweet {
    long counter;
    int tweetId;


    public Tweet(long counter, int tweetId) {
        this.counter = counter;
        this.tweetId = tweetId;
    }
}
