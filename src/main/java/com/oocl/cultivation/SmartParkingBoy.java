package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot plot1) {
        super(plot1);
    }

    public ParkingLot getMoreEmptyPlot() {
        ParkingLot packingLot = null;
        for (ParkingLot lot : this.getPackingLots()) {
            if (lot.getAvailableSize() > 0
                    && (packingLot == null || packingLot.getAvailableSize() < lot.getAvailableSize())) {
                packingLot = lot;
            }
        }
        return packingLot;
    }

    @Override
    public ParkingTicket parking(Car car) throws NoPositionException {
        ParkingLot packingLot = getMoreEmptyPlot();
        if (packingLot != null) {
            return packingLot.packACar(car);
        }
        throw new NoPositionException();
    }
}
