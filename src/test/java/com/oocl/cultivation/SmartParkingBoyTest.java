package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class SmartParkingBoyTest {
    @Test
    public void should_return_plot2_when_get_more_empty_plot_given_smart_parking_boy(){
        //given
        PackingLot plot1 = mock(PackingLot.class);
        PackingLot plot2 = mock(PackingLot.class);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(plot1);
        smartParkingBoy.addPackingLot(plot2);
        given(plot1.getSize()).willReturn(2);
        given(plot2.getSize()).willReturn(4);

        //when
        PackingLot packingLot_target = smartParkingBoy.getMoreEmptyPlot();

        //then
        assertEquals(plot2,packingLot_target);

    }
}
