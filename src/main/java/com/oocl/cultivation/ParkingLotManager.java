package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingLotManager extends ParkingBoy {
    private final List<ParkAble> parkerList;

    public ParkingLotManager() {
        super();
        this.parkerList = new ArrayList<>();
    }

    public int addParker(ParkAble... parker) {
        parkerList.addAll(Arrays.asList(parker));
        return parkerList.size();
    }

    public ParkingTicket specifyPackerParking(ParkAble parkAble, Car car) throws NoPositionException {
        if (parkerList.contains(parkAble)) {
            return parkAble.parking(car);
        }
        return null;
    }

    @Override
    public Car fetch(ParkingTicket ticket) throws UnrecognizedException, ProvideTicketException {
        if (ticket == null) {
            throw new ProvideTicketException();
        }
        Car car = null;
        try {
            car = super.fetch(ticket);
        } catch (Exception ignored) {

        }
        if (car != null) {
            return car;
        }
        for (ParkAble parkAble : parkerList) {
            try {
                car = parkAble.fetch(ticket);
            } catch (Exception ignored) {
            }
        }
        if (car != null) {
            return car;
        }
        throw new UnrecognizedException();
    }
}
