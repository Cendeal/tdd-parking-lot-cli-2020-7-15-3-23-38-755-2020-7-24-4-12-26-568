package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SuperSmartPackingBoyTest {
    @Test
    public void should_return_large_rate_lot_when_get_lager_lot_given_super_smart_boy(){
        //given
        PackingLot packingLot1 = mock(PackingLot.class);
        SuperSmartPackingBoy superSmartPackingBoy = new SuperSmartPackingBoy(packingLot1);
        PackingLot packingLot2 = mock(PackingLot.class);
        superSmartPackingBoy.addPackingLot(packingLot2);
        given(packingLot1.getSize()).willReturn(8);
        given(packingLot1.getCapacity()).willReturn(10);
        given(packingLot2.getSize()).willReturn(10);
        given(packingLot2.getCapacity()).willReturn(10);

        //when
        PackingLot packingLotActual =superSmartPackingBoy.getLagerLot();
        //then
        assertEquals(packingLot2,packingLotActual);
    }
}
