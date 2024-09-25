package com.example.number_printer;

public class Printer implements Runnable {

    private State state;
    private PrinterType printerType;
    private PrinterType nextPrinterType;
    private int step;
    private int currentNumber;
    private int maxValue;

    public Printer(State state, PrinterType printerType, PrinterType nextPrinterType, int step, int currentNumber, int maxValue) {
        this.state = state;
        this.printerType = printerType;
        this.nextPrinterType = nextPrinterType;
        this.step = step;
        this.currentNumber = currentNumber;
        this.maxValue = maxValue;
    }


    @Override
    public void run() {
        while(currentNumber <= maxValue) {
            synchronized (state) {

                while (printerType != state.getCurrentPrinterType()) {
                    try {
                        state.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(printerType + " : " + currentNumber);
                currentNumber += step;
                state.setCurrentPrinterType(nextPrinterType);
                state.notify();

            }
        }


    }
}
