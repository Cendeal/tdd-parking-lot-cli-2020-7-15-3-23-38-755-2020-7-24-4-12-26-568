package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;

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

    public ParkingTicket specifyPackerParking(ParkAble parkAble, Car car) throws NoPositionException {
        return parkAble.parking(car);
    }
}
