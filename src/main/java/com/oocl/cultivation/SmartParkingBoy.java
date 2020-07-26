package com.oocl.cultivation;

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
}
