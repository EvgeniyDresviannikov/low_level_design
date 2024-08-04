package com.example.parking.lot.terminal;

import com.example.parking.lot.ParkingSpotType;
import com.example.parking.lot.ParkingTicket;

public class EntryTerminal extends Terminal {
    public EntryTerminal(Integer id) {
        super(id);
    }

    public ParkingTicket getTicket(ParkingSpotType spotType) {
        return null;
    }
}
