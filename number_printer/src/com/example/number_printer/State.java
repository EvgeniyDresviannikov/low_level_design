package com.example.number_printer;

public class State {
    private PrinterType currentPrinterType;

    public State(PrinterType currentPrinterType) {
        this.currentPrinterType = currentPrinterType;
    }

    public PrinterType getCurrentPrinterType() {
        return currentPrinterType;
    }

    public void setCurrentPrinterType(PrinterType currentPrinterType) {
        this.currentPrinterType = currentPrinterType;
    }
}
