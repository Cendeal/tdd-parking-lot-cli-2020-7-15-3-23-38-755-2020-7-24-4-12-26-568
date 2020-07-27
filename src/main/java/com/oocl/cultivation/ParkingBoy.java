package com.oocl.cultivation;


import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.LinkedList;
import java.util.List;

public class ParkingBoy {
    private final List<PackingLot> packingLots;

    public ParkingBoy(PackingLot packingLot) {
        this.packingLots = new LinkedList<>();
        this.packingLots.add(packingLot);
    }

    public PackingTicket parking(Car car) throws NoPositionException {
        PackingLot packingLot = this.packingLots.stream().filter(lot -> lot.getAvailableSize() > 0).findFirst().orElse(null);
        if (packingLot != null) {
            return packingLot.packACar(car);
        }
        throw new NoPositionException();
    }

    public Car fetch(PackingTicket ticket) throws UnrecognizedException, ProvideTicketException {
        if (ticket == null) {
            throw new ProvideTicketException();
        }
        Car car = null;
        for (PackingLot packingLot : this.getPackingLots()) {
            car = packingLot.getCar(ticket);
            if (car != null) {
                break;
            }
        }
        return car;
    }

    public boolean addPackingLot(PackingLot packingLot) {
        return this.packingLots.add(packingLot);
    }

    public List<PackingLot> getPackingLots() {
        return packingLots;
    }
}
