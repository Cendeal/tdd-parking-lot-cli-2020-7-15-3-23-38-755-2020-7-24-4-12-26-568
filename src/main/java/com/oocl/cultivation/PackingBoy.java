package com.oocl.cultivation;


import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

public class PackingBoy {
    private final PackingLot packingLot;

    public PackingBoy(PackingLot packingLot) {
        this.packingLot = packingLot;
    }

    public PackingTicket parking(Car car) throws NoPositionException {
        return this.packingLot.packACar(car);
    }

    public Car fetch(PackingTicket ticket) throws UnrecognizedException, ProvideTicketException {
        if (ticket == null) {
            throw new ProvideTicketException();
        }
        return this.packingLot.getCar(ticket);
    }

    public boolean addPackingLot(PackingLot packingLot2) {
        return false;
    }
}
