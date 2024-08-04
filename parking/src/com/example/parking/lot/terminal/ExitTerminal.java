package com.example.parking.lot.terminal;

import com.example.parking.lot.ParkingTicket;

public class ExitTerminal extends Terminal{
    public ExitTerminal(Integer id) {
        super(id);
    }

    public boolean acceptTicket(ParkingTicket ticket) {
        return true;
    }
}
