package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

public class SuperSmartPackingBoy extends PackingBoy {
    public SuperSmartPackingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    @Override
    public PackingTicket parking(Car car) throws NoPositionException {
        PackingLot packingLot = getLagerLot();
        if (packingLot != null) {
            return packingLot.packACar(car);
        }
        throw new NoPositionException();
    }

    public PackingLot getLagerLot() {
        PackingLot current = null;
        for (PackingLot packingLot : this.getPackingLots()) {
            if (packingLot.getAvailableSize() > 0 &&
                    (current == null || packingLot.getAvailableSize() / packingLot.getCapacity() > current.getAvailableSize() / current.getCapacity()))
                current = packingLot;
        }
        return current;
    }
}
