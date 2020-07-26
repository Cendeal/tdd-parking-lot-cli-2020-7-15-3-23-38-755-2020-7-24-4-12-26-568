package com.oocl.cultivation;

public class SuperSmartPackingBoy extends PackingBoy {
    public SuperSmartPackingBoy(PackingLot packingLot) {
        super(packingLot);
    }

    public PackingLot getLagerLot() {
        PackingLot current = null;
        for (PackingLot packingLot : this.getPackingLots()) {
            if (current == null || packingLot.getAvailableSize() / packingLot.getCapacity() > current.getAvailableSize() / current.getCapacity())
                current = packingLot;
        }
        return current;
    }
}
