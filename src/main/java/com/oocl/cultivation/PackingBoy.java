package com.oocl.cultivation;

import java.util.LinkedList;
import java.util.List;

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

    public List<PackingTicket> parking(Car[] cars) {
        List<PackingTicket> packingTicketList = new LinkedList<>();
        for (Car car : cars) {
            packingTicketList.add(this.parking(car));
        }
        return packingTicketList;
    }
}
