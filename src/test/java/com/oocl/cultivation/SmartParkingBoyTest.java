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
        PackingLot plot1 = mock(PackingLot.class);
        PackingLot plot2 = mock(PackingLot.class);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(plot1);
        smartParkingBoy.addPackingLot(plot2);
        given(plot1.getAvailableSize()).willReturn(2);
        given(plot2.getAvailableSize()).willReturn(4);

        //when
        PackingLot packingLot_target = smartParkingBoy.getMoreEmptyPlot();

        //then
        assertEquals(plot2,packingLot_target);
    }

    @Test
    public void should_return_ticket_from_plot2_when_smart_packing_boy_packing_car_given_car() throws NoPositionException {
        //given
        Car car= new Car();
        PackingLot plot1 = mock(PackingLot.class);
        PackingLot plot2 = mock(PackingLot.class);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(plot1);
        smartParkingBoy.addPackingLot(plot2);
        given(plot1.getAvailableSize()).willReturn(2);
        given(plot2.getAvailableSize()).willReturn(4);

        //when
        PackingTicket plot1_ticket= mock(PackingTicket.class);
        PackingTicket plot2_ticket= mock(PackingTicket.class);
        when(plot1.packACar(car)).thenReturn(plot1_ticket);
        when(plot2.packACar(car)).thenReturn(plot2_ticket);
        PackingTicket actual_ticket = smartParkingBoy.parking(car);

        //then
        assertEquals(plot2_ticket,actual_ticket);
    }

    @Test
    void should_throw_error_when_fetching_car_given_null() throws UnrecognizedException, NoPositionException {
        //given
        Car car = new Car();
        PackingLot packingLot = mock(PackingLot.class);
        PackingTicket ticket_mock = mock(PackingTicket.class);
        given(packingLot.packACar(car)).willReturn(ticket_mock);
        given(packingLot.getCar(ticket_mock)).willReturn(car);
        SmartParkingBoy packingBoy = new SmartParkingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

        //when
        ProvideTicketException ticketException = assertThrows(ProvideTicketException.class,()->{
            Car actual = packingBoy.fetch(null);
        });
        //then
        assertEquals("Please provide your parking ticket.",ticketException.getMessage());
    }
}
