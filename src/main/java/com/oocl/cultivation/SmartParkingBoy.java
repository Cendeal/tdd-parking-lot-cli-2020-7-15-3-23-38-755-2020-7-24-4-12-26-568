package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

public class SmartParkingBoy extends PackingBoy {
    public SmartParkingBoy(PackingLot plot1) {
        super(plot1);
    }

    public PackingLot getMoreEmptyPlot() {
        PackingLot packingLot = null;
        for (PackingLot lot : this.getPackingLots()) {
            if (lot.getAvailableSize() > 0
                    && (packingLot == null || packingLot.getAvailableSize() < lot.getAvailableSize())) {
                packingLot = lot;
            }
        }
        return packingLot;
    }

    @Override
    public PackingTicket parking(Car car) throws NoPositionException {
        PackingLot packingLot = getMoreEmptyPlot();
        if (packingLot != null) {
            return packingLot.packACar(car);
        }
        throw new NoPositionException();
    }
}
