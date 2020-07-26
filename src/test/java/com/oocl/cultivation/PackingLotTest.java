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
        PackingLot packingLot = new PackingLot();
        //when
        PackingTicket ticket = packingLot.packACar(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_get_car_given_packing_ticket() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);

        //when
        Car actual = packingLot.getCar(ticket);
        //then
        assertNotNull(actual);
        assertEquals(car, actual);
    }

    @Test
    void should_return_right_car_witch_right_ticket_when_park_multi_cars_given_cars() throws UnrecognizedException, NoPositionException {
        //given
        PackingLot packingLot = new PackingLot();
        Car car1 = new Car();
        Car car2 = new Car();
        PackingTicket ticket1 = packingLot.packACar(car1);
        PackingTicket ticket2 = packingLot.packACar(car2);

        //when
        Car fetchCar1 = packingLot.getCar(ticket1);
        Car fetchCar2 = packingLot.getCar(ticket2);
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
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);

        //when
        packingLot.getCar(ticket);
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.getCar(ticket);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }


    @Test
    void should_throw_error_when_get_car_given_wrong_ticket() throws NoPositionException {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);
        PackingTicket ticket1 = mock(PackingTicket.class);

        //when
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.getCar(ticket1);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_error_when_get_car_given_null() throws NoPositionException {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);

        //when
        UnrecognizedException exception = assertThrows(UnrecognizedException.class, () -> {
            Car fetchedCar = packingLot.getCar(null);
        });
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    void should_throw_error_when_pack_car_given_more_than_10_cars() throws NoPositionException {
        //given
        PackingLot packingLot = new PackingLot();
        //when
        for (int i = 0; i < 10; i++) {
            packingLot.packACar(new Car());
        }
        NoPositionException noPositionException = assertThrows(NoPositionException.class, () -> {
            PackingTicket ticket = packingLot.packACar(new Car());
        });
        //then
        assertEquals("Not enough position.", noPositionException.getMessage());
    }

    @Test
    public void should_return_9_when_get_size_given_car_and_packing_after() throws NoPositionException {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        packingLot.packACar(car);
        //when
        int actual = packingLot.getSize();
        //then
        assertEquals(9,actual);
    }
}