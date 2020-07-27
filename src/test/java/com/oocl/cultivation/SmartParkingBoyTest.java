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

public class SmartParkingBoyTest {
    @Test
    public void should_return_plot2_when_get_more_empty_plot_given_smart_parking_boy(){
        //given
        ParkingLot plot1 = mock(ParkingLot.class);
        ParkingLot plot2 = mock(ParkingLot.class);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(plot1);
        smartParkingBoy.addPackingLot(plot2);
        given(plot1.getAvailableSize()).willReturn(2);
        given(plot2.getAvailableSize()).willReturn(4);

        //when
        ParkingLot packingLotTarget = smartParkingBoy.getMoreEmptyPlot();

        //then
        assertEquals(plot2,packingLotTarget);
    }

    @Test
    public void should_return_ticket_from_plot2_when_smart_packing_boy_packing_car_given_car() throws NoPositionException {
        //given
        Car car= new Car();
        ParkingLot plot1 = mock(ParkingLot.class);
        ParkingLot plot2 = mock(ParkingLot.class);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(plot1);
        smartParkingBoy.addPackingLot(plot2);
        given(plot1.getAvailableSize()).willReturn(2);
        given(plot2.getAvailableSize()).willReturn(4);

        //when
        ParkingTicket plot1Ticket= mock(ParkingTicket.class);
        ParkingTicket plot2Ticket= mock(ParkingTicket.class);
        when(plot1.parking(car)).thenReturn(plot1Ticket);
        when(plot2.parking(car)).thenReturn(plot2Ticket);
        ParkingTicket actualTicket = smartParkingBoy.parking(car);

        //then
        assertEquals(plot2Ticket,actualTicket);
    }

    @Test
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticketMock = mock(ParkingTicket.class);
        given(packingLot.parking(car)).willReturn(ticketMock);
        given(packingLot.fetch(ticketMock)).willReturn(car);
        SmartParkingBoy packingBoy = new SmartParkingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(10);

        ParkingTicket ticket = packingBoy.parking(car);

        //when
        ProvideTicketException ticketException = assertThrows(ProvideTicketException.class,()->{
            Car actual = packingBoy.fetch(null);
        });
        //then
        assertEquals("Please provide your parking ticket.",ticketException.getMessage());
    }

    @Test
    void should_throw_error_when_parking_car_given_more_than_10_cars() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        ParkingLot packingLot = mock(ParkingLot.class);
        SmartParkingBoy packingBoy = new SmartParkingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(0);
        //when
        NoPositionException exception = assertThrows(NoPositionException.class, () -> {
            packingBoy.parking(new Car());
        });
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
