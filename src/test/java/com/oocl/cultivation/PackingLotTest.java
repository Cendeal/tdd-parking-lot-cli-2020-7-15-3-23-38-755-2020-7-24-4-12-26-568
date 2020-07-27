package com.oocl.cultivation;


import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.UnrecognizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class PackingLotTest {

    @Test
    void should_return_ticket_when_park_car_given_car() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        //when
        ParkingTicket ticket = packingLot.parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_get_car_given_packing_ticket() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        ParkingTicket ticket = packingLot.parking(car);

        //when
        Car actual = packingLot.fetch(ticket);
        //then
        assertNotNull(actual);
        assertEquals(car, actual);
    }

    @Test
    void should_return_right_car_witch_right_ticket_when_park_multi_cars_given_cars() throws UnrecognizedException, NoPositionException {
        //given
        ParkingLot packingLot = new ParkingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        ParkingTicket ticket1 = packingLot.parking(car1);
        ParkingTicket ticket2 = packingLot.parking(car2);

        //when
        Car fetchCar1 = packingLot.fetch(ticket1);
        Car fetchCar2 = packingLot.fetch(ticket2);
        //then
        assertNotNull(fetchCar1);
        assertNotNull(fetchCar2);
        assertNotEquals(fetchCar1, fetchCar2);
        assertEquals(car1, fetchCar1);
        assertEquals(car2, fetchCar2);
    }

    @Test
    void should_throw_error_when_get_car_given_has_used_packing_ticket() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        ParkingTicket ticket = packingLot.parking(car);

        //when
        packingLot.fetch(ticket);
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.fetch(ticket);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_throw_error_when_get_car_given_wrong_ticket() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        ParkingTicket ticket = packingLot.parking(car);
        ParkingTicket ticket1 = mock(ParkingTicket.class);

        //when
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.fetch(ticket1);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_error_when_get_car_given_null() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        ParkingTicket ticket = packingLot.parking(car);

        //when
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.fetch(null);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_error_when_pack_car_given_more_than_10_cars() throws NoPositionException {
        //given
        ParkingLot packingLot = new ParkingLot();
        //when
        for (int i = 0; i < 10; i++) {
            packingLot.parking(new Car());
        }
        NoPositionException noPositionException = assertThrows(NoPositionException.class, () -> {
            ParkingTicket ticket = packingLot.parking(new Car());
        });
        //then
        assertEquals("Not enough position.", noPositionException.getMessage());
    }

    @Test
    public void should_return_9_when_get_size_given_car_and_packing_after() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = new ParkingLot();
        packingLot.parking(car);
        //when
        int actual = packingLot.getAvailableSize();
        //then
        assertEquals(9,actual);
    }

    @Test
    public void should_return_10_when_getCapacity_given_packing_lot() {
        //given
        ParkingLot packingLot = new ParkingLot();
        //when
        int actual = packingLot.getCapacity();
        //then
        assertEquals(10,actual);
    }

    @Test
    public void should_return_zero_point_six_when_get_available_position_rate_given_packing_lot() throws NoPositionException {
        //given
        ParkingLot packingLot = new ParkingLot();
        for(int i=0;i<4;i++){
            packingLot.parking(new Car());
        }
        //when
        double rate = packingLot.getAvailablePositionRate();
        //then
        assertEquals(0.6,rate);

    }
}