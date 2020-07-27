package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotManager {
    private final List<ParkAble> parkerList;

    public ParkingLotManager() {
        this.parkerList = new ArrayList<>();
    }

    public int addParker(ParkAble... parker) {
        parkerList.addAll(Arrays.asList(parker));
        return parkerList.size();
    }
}
