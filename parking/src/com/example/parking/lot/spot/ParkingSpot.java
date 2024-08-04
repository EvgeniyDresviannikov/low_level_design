package com.example.parking.lot.spot;

import com.example.parking.lot.ParkingSpotType;
import com.example.parking.lot.terminal.EntryTerminal;

public abstract class ParkingSpot {
    private String id;
    private boolean isFree;
    private double distance;
    private ParkingSpotType parkingSpotType;

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    private EntryTerminal terminal;

    public boolean isFree() {
        return isFree;
    }

    public EntryTerminal getTerminal() {
        return terminal;
    }

    abstract double getRate();
}
