package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot packingLot) {
        super(packingLot);
    }

    @Override
    public ParkingTicket parking(Car car) throws NoPositionException {
        ParkingLot packingLot = getLagerLot();
        if (packingLot != null) {
            return packingLot.park(car);
        }
        throw new NoPositionException();
    }

    public ParkingLot getLagerLot() {
        ParkingLot current = null;
        for (ParkingLot packingLot : this.getPackingLots()) {
            if (packingLot.getAvailablePositionRate() < 1 &&
                    (current == null || packingLot.getAvailablePositionRate() > current.getAvailablePositionRate()))
                current = packingLot;
        }
        return current;
    }
}