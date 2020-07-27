package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;

public interface ParkAble {
    ParkingTicket parking(Car car) throws NoPositionException;
    Car fetch(ParkingTicket ticket) throws UnrecognizedException, ProvideTicketException;
}
