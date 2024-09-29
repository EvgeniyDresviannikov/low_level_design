package org.example;

import java.util.HashMap;
import java.util.Map;

class FrequencyTracker {

    Map<Integer, Integer> numToFreq;
    Map<Integer, Integer> freqToCount;

    public FrequencyTracker() {
        numToFreq = new HashMap<>();
        freqToCount = new HashMap<>();
    }

    public void add(int number) {
        int cnt = numToFreq.getOrDefault(number, 0) + 1;
        numToFreq.put(number, cnt);
        freqToCount.put(cnt, freqToCount.getOrDefault(cnt, 0) + 1);
        freqToCount.put(cnt-1, freqToCount.getOrDefault(cnt-1, 1) - 1);
    }

    public void deleteOne(int number) {
        if (!numToFreq.containsKey(number) || numToFreq.get(number) == 0) return;

        int cnt = numToFreq.get(number);
        freqToCount.put(cnt, freqToCount.get(cnt) - 1);
        freqToCount.put(cnt-1, freqToCount.getOrDefault(cnt-1, 0) + 1);
        numToFreq.put(number, cnt - 1);

    }

    public boolean hasFrequency(int frequency) {
        return freqToCount.getOrDefault(frequency, 0) > 0;
    }
}

/**
 * Your FrequencyTracker object will be instantiated and called as such:
 * FrequencyTracker obj = new FrequencyTracker();
 * obj.add(number);
 * obj.deleteOne(number);
 * boolean param_3 = obj.hasFrequency(frequency);
 */