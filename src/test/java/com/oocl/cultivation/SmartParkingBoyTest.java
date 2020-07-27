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
        ParkingLot packingLot_target = smartParkingBoy.getMoreEmptyPlot();

        //then
        assertEquals(plot2,packingLot_target);
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
        ParkingTicket plot1_ticket= mock(ParkingTicket.class);
        ParkingTicket plot2_ticket= mock(ParkingTicket.class);
        when(plot1.park(car)).thenReturn(plot1_ticket);
        when(plot2.park(car)).thenReturn(plot2_ticket);
        ParkingTicket actual_ticket = smartParkingBoy.parking(car);

        //then
        assertEquals(plot2_ticket,actual_ticket);
    }

    @Test
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        ParkingLot packingLot = mock(ParkingLot.class);
        ParkingTicket ticket_mock = mock(ParkingTicket.class);
        given(packingLot.park(car)).willReturn(ticket_mock);
        given(packingLot.getCar(ticket_mock)).willReturn(car);
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
