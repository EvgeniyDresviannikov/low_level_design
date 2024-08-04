package com.example.parking.lot;

import com.example.parking.lot.spot.ParkingSpot;
import com.example.parking.lot.terminal.EntryTerminal;

public interface ParkingAssignmentStrategy {

    ParkingSpot getParkingSpot(EntryTerminal entryTerminal, ParkingSpotType parkingSpotType);

    void releaseParkingSpot(ParkingSpot parkingSpot);
}
