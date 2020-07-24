package com.oocl.cultivation.test;

import com.oocl.cultivation.Car;
import com.oocl.cultivation.PackingBoy;
import com.oocl.cultivation.PackingLot;
import com.oocl.cultivation.PackingTicket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ParkingBoyFacts {
    @Test
    void should_return_car_when_packing_car_given_car() {
        //given
        Car car = new Car();
        PackingLot packingLot = new PackingLot();
        PackingBoy packingBoy = new PackingBoy(packingLot);
        //when
        PackingTicket ticket = packingBoy.parking(car);
        //then
        assertNotNull(ticket);
    }
}
