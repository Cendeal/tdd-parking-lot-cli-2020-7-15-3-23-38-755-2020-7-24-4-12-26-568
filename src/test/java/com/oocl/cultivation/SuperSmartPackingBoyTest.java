package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SuperSmartPackingBoyTest {
    @Test
    public void should_return_large_rate_lot_when_get_lager_lot_given_super_smart_boy() {
        //given
        ParkingLot packingLot1 = mock(ParkingLot.class);
        SuperSmartParkingBoy superSmartPackingBoy = new SuperSmartParkingBoy(packingLot1);
        ParkingLot packingLot2 = mock(ParkingLot.class);
        superSmartPackingBoy.addPackingLot(packingLot2);

        given(packingLot1.getAvailablePositionRate()).willReturn(0.6);
        given(packingLot2.getAvailablePositionRate()).willReturn(0.8);

        //when
        ParkingLot packingLotActual = superSmartPackingBoy.getLagerLot();
        //then
        assertEquals(packingLot2, packingLotActual);
    }

    @Test
    public void should_return_ticket_from_plot2_when_super_smart_packing_boy_packing_car_given_car() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot1 = mock(ParkingLot.class);
        ParkingLot packingLot2 = mock(ParkingLot.class);
        SuperSmartParkingBoy superSmartPackingBoy = new SuperSmartParkingBoy(packingLot1);
        superSmartPackingBoy.addPackingLot(packingLot2);

        given(packingLot1.getAvailablePositionRate()).willReturn(0.6);
        given(packingLot2.getAvailablePositionRate()).willReturn(0.8);

        //when
        ParkingTicket plot1_ticket = mock(ParkingTicket.class);
        ParkingTicket plot2_ticket = mock(ParkingTicket.class);
        when(packingLot1.parking(car)).thenReturn(plot1_ticket);
        when(packingLot2.parking(car)).thenReturn(plot2_ticket);
        ParkingTicket actual_ticket = superSmartPackingBoy.parking(car);

        //then
        assertEquals(plot2_ticket, actual_ticket);
    }

    @Test
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticket_mock = mock(ParkingTicket.class);
        given(packingLot.parking(car)).willReturn(ticket_mock);
        given(packingLot.fetch(ticket_mock)).willReturn(car);
        SuperSmartParkingBoy packingBoy = new SuperSmartParkingBoy(packingLot);
        given(packingLot.getAvailablePositionRate()).willReturn(1.0);
        ParkingTicket ticket = packingBoy.parking(car);

        //when
        ProvideTicketException ticketException = assertThrows(ProvideTicketException.class, () -> {
            Car actual = packingBoy.fetch(null);
        });
        //then
        assertEquals("Please provide your parking ticket.", ticketException.getMessage());
    }

    @Test
    void should_throw_error_when_parking_car_given_more_than_10_cars() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        SuperSmartParkingBoy packingBoy = new SuperSmartParkingBoy(packingLot);
        given(packingLot.getAvailablePositionRate()).willReturn(0.0);
        //when
        NoPositionException exception = assertThrows(NoPositionException.class, () -> {
            packingBoy.parking(new Car());
        });
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
