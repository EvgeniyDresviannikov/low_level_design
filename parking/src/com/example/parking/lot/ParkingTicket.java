package com.example.parking.lot;

import com.example.parking.lot.spot.ParkingSpot;

import java.sql.Timestamp;

public class ParkingTicket {
    private String id;
    private ParkingSpot parkingSpot;
    private Timestamp entranceTime;

    public ParkingTicket(String id, ParkingSpot parkingSpot, Timestamp entranceTime) {
        this.id = id;
        this.parkingSpot = parkingSpot;
        this.entranceTime = entranceTime;
    }
}
