package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyTest {
    @Test
    void should_return_ticket_when_packing_car_given_car() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        //when
        PackingTicket ticket = packingBoy.parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetching_car_given_packing_ticket() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        Car actual = packingBoy.fetch(ticket);
        //then
        assertNotNull(actual);
        assertEquals(car, actual);
    }


    @Test
    void should_return_right_car_witch_right_ticket_when_parking_multi_cars_given_cars() {
        //given
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        Car car1 = new Car();
        Car car2 = new Car();
        PackingTicket ticket1 = packingBoy.parking(car1);
        PackingTicket ticket2 = packingBoy.parking(car2);

        //when
        Car fetchCar1 = packingBoy.fetch(ticket1);
        Car fetchCar2 = packingBoy.fetch(ticket2);
        //then
        assertNotNull(fetchCar1);
        assertNotNull(fetchCar2);
        assertNotEquals(fetchCar1, fetchCar2);
        assertEquals(car1, fetchCar1);
        assertEquals(car2, fetchCar2);
    }


    @Test
    void should_return_null_when_fetching_car_given_wrong_ticket() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        Car car_null = packingBoy.fetch(null);
        Car car_new_ticket = packingBoy.fetch(new PackingTicket());
        //then
        assertNull(car_null);
        assertNull(car_new_ticket);
    }

    @Test
    void should_return_null_when_fetching_car_given_has_used_packing_ticket() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        packingBoy.fetch(ticket);
        Car fetchedCar = packingBoy.fetch(ticket);
        //then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_null_when_packing_car_given_more_than_10_cars() {
        //given
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        //when
        for (int i = 0; i < 10; i++) {
            packingBoy.parking(new Car());
        }
        PackingTicket ticket = packingBoy.parking(new Car());
        //then
        assertNull(ticket);
    }
}
