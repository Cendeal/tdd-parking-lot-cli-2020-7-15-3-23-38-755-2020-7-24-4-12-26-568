package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackingLotTest {
    @Test
    void should_return_null_when_pack_car_given_more_than_10_cars() {
        //given
        PackingLot packingLot = new PackingLot();
        //when
        for (int i = 0; i < 10; i++) {
            packingLot.packACar(new Car());
        }
        PackingTicket ticket = packingLot.packACar(new Car());
        //then
        assertNull(ticket);
    }

    @Test
    void should_return_null_when_get_car_given_has_used_packing_ticket() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);

        //when
        packingLot.getCar(ticket);
        Car fetchedCar = packingLot.getCar(ticket);
        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_null_when_get_car_given_wrong_ticket() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingTicket ticket = packingLot.packACar(car);

        //when
        Car car_null = packingLot.getCar(null);
        Car car_new_ticket = packingLot.getCar(new PackingTicket());
        //then
        assertNull(car_null);
        assertNull(car_new_ticket);
    }

    @Test
    void should_return_right_car_witch_right_ticket_when_park_multi_cars_given_cars() {
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
    void should_return_ticket_when_park_car_given_car() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        //when
        PackingTicket ticket = packingLot.packACar(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_get_car_given_packing_ticket() {
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
}