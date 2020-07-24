package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.PackingBoy;
import com.oocl.cultivation.PackingLot;
import com.oocl.cultivation.PackingTicket;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingBoyFacts {
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
        assertEquals(car,actual);
    }


    @Test
    void should_return_tickets_when_parking_cars_given_cars() {
        //given
        Car[] cars = {
                new Car(),
                new Car(),
                new Car()
        };
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        List<PackingTicket> ticket = packingBoy.parking(cars);

        //when
        Car actual = packingBoy.fetch(ticket.get(0));
        //then
        assertNotNull(actual);
        assertEquals(cars[0],actual);
    }
}
