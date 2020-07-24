package com.oocl.cultivation;

public class PackingBoy {
    private final PackingLot packingLot;
    public PackingBoy(PackingLot packingLot) {
        this.packingLot = packingLot;
    }

    public PackingTicket parking(Car car) {

        return this.packingLot.packACar(car);
    }

    public Car fetch(PackingTicket ticket) {
        return this.packingLot.getCar(ticket);
    }
}
