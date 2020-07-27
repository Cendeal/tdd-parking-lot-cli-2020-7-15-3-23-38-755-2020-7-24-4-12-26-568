package com.oocl.cultivation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest {
    @Test
    public void should_return_4_when_add_parker_given_4_parker(){
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        //when
        int count = parkingLotManager.addParker(parkingLot,parkingBoy,smartParkingBoy,superSmartParkingBoy);
        //then
        assertEquals(4,count);
    }

}