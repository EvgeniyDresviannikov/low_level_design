package com.example.parking.lot;

import com.example.parking.lot.spot.ParkingSpot;
import com.example.parking.lot.terminal.EntryTerminal;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ParkingSpotNearestEntranceStrategy implements ParkingAssignmentStrategy {

    private Map<String, Queue<ParkingSpot>> nearestParkingSpots;
    private Set<ParkingSpot> availableParkingSpots;
    private Set<ParkingSpot> reservedParkingSpots;


    @Override
    public ParkingSpot getParkingSpot(EntryTerminal entryTerminal, ParkingSpotType parkingSpotType) {
        String key = "" + entryTerminal.getId() + parkingSpotType.toString();
        ParkingSpot nearest = nearestParkingSpots.get(key).poll();
        availableParkingSpots.remove(nearest);
        reservedParkingSpots.add(nearest);
        return nearest;
    }

    @Override
    public void releaseParkingSpot(ParkingSpot parkingSpot) {
        EntryTerminal terminal = parkingSpot.getTerminal();
        reservedParkingSpots.remove(parkingSpot);
        availableParkingSpots.add(parkingSpot);
        String key = "" + terminal.getId() + parkingSpot.getParkingSpotType();
        nearestParkingSpots.get(key).add(parkingSpot);
    }
}
