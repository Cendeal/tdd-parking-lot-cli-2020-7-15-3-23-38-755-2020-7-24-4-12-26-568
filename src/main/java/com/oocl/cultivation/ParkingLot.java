package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ground;
    private final int capacity;

    public ParkingLot() {
        this.ground = new HashMap<>();
        this.capacity = 10;
    }

    public ParkingTicket packACar(Car car) throws NoPositionException {
        if (this.ground.size() < this.capacity) {
            ParkingTicket ticket = new ParkingTicket();
            this.ground.put(ticket, car);
            return ticket;
        }
        throw new NoPositionException();
    }

    public Car getCar(ParkingTicket ticket) throws UnrecognizedException {
        if(!this.ground.containsKey(ticket)){
            throw new UnrecognizedException();
        }
        return this.ground.remove(ticket);
    }

    public int getAvailableSize() {
        return this.capacity - this.ground.size();
    }

    public double getAvailablePositionRate(){
        return (double) this.getAvailableSize() / this.getCapacity();
    }

    public int getCapacity() {
        return this.capacity;
    }
}
