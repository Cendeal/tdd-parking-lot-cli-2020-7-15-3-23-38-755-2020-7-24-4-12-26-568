package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
