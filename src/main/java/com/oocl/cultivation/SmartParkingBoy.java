package com.oocl.cultivation;

public class SmartParkingBoy extends PackingBoy{
    public SmartParkingBoy(PackingLot plot1) {
        super(plot1);
    }

    public PackingLot getMoreEmptyPlot() {
        return null;
    }
}
