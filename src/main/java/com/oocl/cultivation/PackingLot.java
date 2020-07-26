package com.oocl.cultivation;

import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.HashMap;
import java.util.Map;

public class PackingLot {
    private final Map<PackingTicket, Car> ground;
    private final int capacity;

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

    public Car getCar(PackingTicket ticket) throws UnrecognizedException {
        if(!this.ground.containsKey(ticket)){
            throw new UnrecognizedException();
        }
        return this.ground.remove(ticket);
    }
}
