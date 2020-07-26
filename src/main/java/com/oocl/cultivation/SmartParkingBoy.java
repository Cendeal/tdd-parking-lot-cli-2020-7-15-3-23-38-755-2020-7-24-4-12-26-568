package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

public class SmartParkingBoy extends PackingBoy{
    public SmartParkingBoy(PackingLot plot1) {
        super(plot1);
    }

    public PackingLot getMoreEmptyPlot() {
        PackingLot packingLot = null;
        for(PackingLot lot:this.getPackingLots()){
            if(packingLot == null||packingLot.getSize()<lot.getSize()){
                packingLot = lot;
            }
        }
        return packingLot;
    }

    @Override
    public PackingTicket parking(Car car) {
        PackingLot packingLot = getMoreEmptyPlot();
        if(packingLot!=null){
            try {
                return packingLot.packACar(car);
            } catch (NoPositionException ignored) {
            }
        }
        return null;
    }
}
