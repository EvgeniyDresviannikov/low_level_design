package org.example;

public enum LoggerLevel {
    DEBUG(1), INFO(2), WARN(3), ERROR(4), FATAL(5);
    
    private int level;

    LoggerLevel(int level) {
        this.level = level;
    }

    int getLevel() {
        return level;
    }
}
