package org.example;

public class InfoLogger extends AbstractLogger{


    protected InfoLogger() {
       super(LoggerLevel.INFO);
    }

    @Override
    protected void display(String msg, LoggerSubject loggerSubject) {

    }
}
