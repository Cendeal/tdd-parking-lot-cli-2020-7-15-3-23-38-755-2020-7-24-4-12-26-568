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
    public void should_return_large_rate_lot_when_get_lager_lot_given_super_smart_boy(){
        //given
        PackingLot packingLot1 = mock(PackingLot.class);
        SuperSmartPackingBoy superSmartPackingBoy = new SuperSmartPackingBoy(packingLot1);
        PackingLot packingLot2 = mock(PackingLot.class);
        superSmartPackingBoy.addPackingLot(packingLot2);

        given(packingLot1.getAvailableSize()).willReturn(8);
        given(packingLot1.getCapacity()).willReturn(10);
        given(packingLot2.getAvailableSize()).willReturn(10);
        given(packingLot2.getCapacity()).willReturn(10);

        //when
        PackingLot packingLotActual = superSmartPackingBoy.getLagerLot();
        //then
        assertEquals(packingLot2,packingLotActual);
    }

    @Test
    public void should_return_ticket_from_plot2_when_super_smart_packing_boy_packing_car_given_car() throws NoPositionException {
        //given
        Car car= new Car();
        PackingLot packingLot1 = mock(PackingLot.class);
        PackingLot packingLot2 = mock(PackingLot.class);
        SuperSmartPackingBoy superSmartPackingBoy = new SuperSmartPackingBoy(packingLot1);
        superSmartPackingBoy.addPackingLot(packingLot2);

        given(packingLot1.getAvailableSize()).willReturn(8);
        given(packingLot1.getCapacity()).willReturn(10);
        given(packingLot2.getAvailableSize()).willReturn(10);
        given(packingLot2.getCapacity()).willReturn(10);

        //when
        PackingTicket plot1_ticket= mock(PackingTicket.class);
        PackingTicket plot2_ticket= mock(PackingTicket.class);
        when(packingLot1.packACar(car)).thenReturn(plot1_ticket);
        when(packingLot2.packACar(car)).thenReturn(plot2_ticket);
        PackingTicket actual_ticket = superSmartPackingBoy.parking(car);

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
        SuperSmartPackingBoy packingBoy = new SuperSmartPackingBoy(packingLot);
        PackingTicket ticket = packingBoy.parking(car);

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
        PackingLot packingLot = mock(PackingLot.class);
        SuperSmartPackingBoy packingBoy = new SuperSmartPackingBoy(packingLot);
        given(packingLot.getAvailableSize()).willReturn(0);
        //when
        NoPositionException exception = assertThrows(NoPositionException.class, () -> {
            packingBoy.parking(new Car());
        });
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }
}
