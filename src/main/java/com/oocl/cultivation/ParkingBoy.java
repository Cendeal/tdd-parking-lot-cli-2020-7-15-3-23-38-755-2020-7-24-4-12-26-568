package com.oocl.cultivation;


import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.LinkedList;
import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> packingLots;

    public ParkingBoy(ParkingLot packingLot) {
        this.packingLots = new LinkedList<>();
        this.packingLots.add(packingLot);
    }

    public ParkingTicket parking(Car car) throws NoPositionException {
        ParkingLot packingLot = this.packingLots.stream().filter(lot -> lot.getAvailableSize() > 0).findFirst().orElse(null);
        if (packingLot != null) {
            return packingLot.parking(car);
        }
        throw new NoPositionException();
    }

    public Car fetch(ParkingTicket ticket) throws UnrecognizedException, ProvideTicketException {
        if (ticket == null) {
            throw new ProvideTicketException();
        }
        Car car = null;
        for (ParkingLot packingLot : this.getPackingLots()) {
            car = packingLot.fetch(ticket);
            if (car != null) {
                break;
            }
        }
        return car;
    }

    public boolean addPackingLot(ParkingLot packingLot) {
        return this.packingLots.add(packingLot);
    }

    public List<ParkingLot> getPackingLots() {
        return packingLots;
    }
}
