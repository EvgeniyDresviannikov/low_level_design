package org.example;

import java.util.HashSet;
import java.util.Set;

public class PhoneDirectory {

    private final Set<Integer> directory;

    public PhoneDirectory(int maxNumbers) {
        directory = new HashSet<>();
        for (int i = 0; i < maxNumbers; i++) {
            directory.add(i);
        }
    }

    public int get() {
        return directory.stream().findFirst().orElse(-1);
    }

    public boolean check(int number) {
        return directory.contains(number);
    }

    public void release(int number) {
        directory.add(number);
    }
}
