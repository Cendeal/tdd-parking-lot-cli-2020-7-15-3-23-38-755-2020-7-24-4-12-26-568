package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class PackingLot {
    private Map<PackingTicket, Car> ground;
    private int capacity;

    public PackingLot() {
        this.ground = new HashMap<>();
        this.capacity = 10;
    }

    public PackingTicket packACar(Car car) {
        if (this.ground.size() < this.capacity) {
            PackingTicket ticket = new PackingTicket();
            this.ground.put(ticket, car);
            return ticket;
        }
        return null;

    }

    public Car getCar(PackingTicket ticket) {
        return this.ground.remove(ticket);
    }
}
