package com.example.parking.lot.terminal;

public abstract class Terminal {
    private Integer id;

    public Terminal(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
