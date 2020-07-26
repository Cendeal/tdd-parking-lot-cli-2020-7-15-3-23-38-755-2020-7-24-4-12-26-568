package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ParkingBoyTest {
    @Test
    void should_return_ticket_when_packing_car_given_car() {
        //given
        Car car = new Car();
        PackingLot packingLot = mock(PackingLot.class);
        PackingTicket ticket_mock = mock(PackingTicket.class);
        given(packingLot.packACar(car)).willReturn(ticket_mock);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        //when
        PackingTicket ticket = packingBoy.parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetching_car_given_packing_ticket() throws UnrecognizedException {
        //given
        Car car = new Car();
        PackingLot packingLot = mock(PackingLot.class);
        PackingTicket ticket_mock = mock(PackingTicket.class);
        given(packingLot.packACar(car)).willReturn(ticket_mock);
        given(packingLot.getCar(ticket_mock)).willReturn(car);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        Car actual = packingBoy.fetch(ticket);
        //then
        assertNotNull(actual);
        assertEquals(car, actual);
    }

    @Test
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException {
        //given
        Car car = new Car();
        PackingLot packingLot = mock(PackingLot.class);
        PackingTicket ticket_mock = mock(PackingTicket.class);
        given(packingLot.packACar(car)).willReturn(ticket_mock);
        given(packingLot.getCar(ticket_mock)).willReturn(car);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        ProvideTicketException ticketException = assertThrows(ProvideTicketException.class,()->{
            Car actual = packingBoy.fetch(null);
        });
        //then
        assertEquals("Please provide your parking ticket.",ticketException.getMessage());

    }
}
