package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ParkingBoyTest {
    @Test
    void should_return_ticket_when_packing_car_given_car() throws NoPositionException {
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
    void should_return_car_when_fetching_car_given_packing_ticket() throws UnrecognizedException, ProvideTicketException, NoPositionException {
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
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException, NoPositionException {
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

    @Test
    void should_return_true_when_add_packing_lot_given_packing_lot(){
        //given
        PackingLot packingLot = mock(PackingLot.class);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingLot packingLot2 = mock(PackingLot.class);

        //when
        boolean actual = packingBoy.addPackingLot(packingLot2);
        //then
        assertTrue(actual);
    }

    @Test
    void should_return_ticket_when_packing_car_given_plot_1_has_no_position_plot2_has_position() throws NoPositionException {
        //given
        PackingLot packingLot = mock(PackingLot.class);
        PackingBoy packingBoy = new PackingBoy(packingLot);
        PackingLot packingLot2 = mock(PackingLot.class);
        packingBoy.addPackingLot(packingLot2);
        Car car = new Car();
        given(packingLot.packACar(car)).willThrow(NoPositionException.class);
        PackingTicket packingTicket = mock(PackingTicket.class);
        given(packingLot2.packACar(car)).willReturn(packingTicket);
        //when
        PackingTicket actual_ticket = packingBoy.parking(car);
        //then
        assertEquals(packingTicket,actual_ticket);
    }
}
