package com.oocl.cultivation;


import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.LinkedList;
import java.util.List;

public class PackingBoy {
    private final List<PackingLot> packingLots;

    public PackingBoy(PackingLot packingLot) {
        this.packingLots = new LinkedList<>();
        this.packingLots.add(packingLot);
    }

    public PackingTicket parking(Car car) throws NoPositionException {
        PackingTicket ticket = null;
        for (PackingLot packingLot : this.packingLots) {
            try {
                ticket = packingLot.packACar(car);
                if (ticket != null) {
                    break;
                }
            } catch (NoPositionException ignored) {
            }
        }
        return ticket;
    }

    public Car fetch(PackingTicket ticket) throws UnrecognizedException, ProvideTicketException {
        if (ticket == null) {
            throw new ProvideTicketException();
        }
        return this.packingLots.get(0).getCar(ticket);
    }

    public boolean addPackingLot(PackingLot packingLot2) {
        return this.packingLots.add(packingLot2);
    }

    public List<PackingLot> getPackingLots() {
        return packingLots;
    }
}
