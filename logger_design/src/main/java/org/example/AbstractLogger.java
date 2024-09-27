package org.example;

public abstract class AbstractLogger {

    private LoggerLevel level;
    private AbstractLogger nextLogger;

    public AbstractLogger(LoggerLevel level) {
        this.level = level;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(String message, LoggerLevel levelToLog, LoggerSubject loggerSubject) {
        if (level == levelToLog) {
            display(message, loggerSubject);
        }

        if (nextLogger != null) {
            nextLogger.logMessage(message, levelToLog, loggerSubject);
        }
    }

    protected abstract void display(String msg, LoggerSubject loggerSubject);
}
