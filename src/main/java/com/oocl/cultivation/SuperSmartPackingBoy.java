package com.oocl.cultivation;

public class SuperSmartPackingBoy extends PackingBoy {
    public SuperSmartPackingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public PackingLot getLagerLot() {
        PackingLot current = null;
        for (PackingLot packingLot : this.getPackingLots()) {
            if (current == null || packingLot.getSize() / packingLot.getCapacity() > current.getSize() / current.getCapacity())
                current = packingLot;
        }
        return current;
    }
}
