package com.oocl.cultivation;

public class PackingBoy {
    public PackingBoy(PackingLot packingLot) {
    }

    public PackingTicket parking(Car car) {
        return new PackingTicket(car);
    }

    public Car fetch(PackingTicket ticket) {
        return null;
    }
}
