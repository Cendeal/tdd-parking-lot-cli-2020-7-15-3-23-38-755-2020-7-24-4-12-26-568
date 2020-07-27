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
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticketMock = mock(ParkingTicket.class);
        given(packingLot.parking(car)).willReturn(ticketMock);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(10);

        //when
        ParkingTicket ticket = packingBoy.parking(car);
        //then
        assertNotNull(ticket);
    }

    @Test
    void should_return_car_when_fetching_car_given_packing_ticket() throws UnrecognizedException, ProvideTicketException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticketMock = mock(ParkingTicket.class);
        given(packingLot.parking(car)).willReturn(ticketMock);
        given(packingLot.fetch(ticketMock)).willReturn(car);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(10);
        ParkingTicket ticket = packingBoy.parking(car);

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
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticketMock = mock(ParkingTicket.class);
        given(packingLot.parking(car)).willReturn(ticketMock);
        given(packingLot.fetch(ticketMock)).willReturn(car);
        given(packingLot.getAvailableSize()).willReturn(10);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        ParkingTicket ticket = packingBoy.parking(car);

        //when
        ProvideTicketException ticketException = assertThrows(ProvideTicketException.class, () -> {
            Car actual = packingBoy.fetch(null);
        });
        //then
        assertEquals("Please provide your parking ticket.", ticketException.getMessage());
    }

    @Test
    void should_return_true_when_add_packing_lot_given_packing_lot() {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        ParkingLot packingLot2 = mock(ParkingLot.class);

        //when
        boolean actual = packingBoy.addPackingLot(packingLot2);
        //then
        assertTrue(actual);
    }

    @Test
    void should_return_ticket_when_packing_car_given_plot1_has_no_position_plot2_has_position() throws NoPositionException {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        ParkingLot packingLot2 = mock(ParkingLot.class);
        packingBoy.addPackingLot(packingLot2);
        Car car = new Car();
        given(packingLot.parking(car)).willThrow(NoPositionException.class);
        ParkingTicket packingTicket = mock(ParkingTicket.class);
        given(packingLot2.getAvailableSize()).willReturn(10);
        given(packingLot2.parking(car)).willReturn(packingTicket);
        //when
        ParkingTicket actualTicket = packingBoy.parking(car);
        //then
        assertEquals(packingTicket, actualTicket);
    }

    @Test
    void should_return_cat_when_fetch_car_given_more_than_10_cars_to_packing() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        ParkingLot packingLot2 = mock(ParkingLot.class);
        packingBoy.addPackingLot(packingLot2);
        Car car = new Car();
        given(packingLot.parking(car)).willThrow(NoPositionException.class);
        ParkingTicket packingTicket = mock(ParkingTicket.class);
        given(packingLot2.parking(car)).willReturn(packingTicket);
        given(packingLot2.fetch(packingTicket)).willReturn(car);
        //when
        Car actualCar = packingBoy.fetch(packingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    void should_throw_error_when_parking_car_given_more_than_10_cars() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingBoy packingBoy = new ParkingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(0);
        //when
        NoPositionException exception = assertThrows(NoPositionException.class, () -> {
            packingBoy.parking(new Car());
        });
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
