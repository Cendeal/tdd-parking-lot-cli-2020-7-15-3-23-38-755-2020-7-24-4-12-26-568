package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotManagerTest extends ParkingBoy{
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

    @Test
    public void should_return_right_car_when_specify_smart_parking_boy_packer_to_parking_given_super_smart_parking_boy() throws UnrecognizedException, ProvideTicketException, NoPositionException {
        //given
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        parkingLotManager.addParker(parkingLot,parkingBoy,smartParkingBoy,superSmartParkingBoy);
        Car parkCar = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotManager.specifyPackerParking(smartParkingBoy,parkCar);
        //then
        Car fetchCar = smartParkingBoy.fetch(parkingTicket);
        assertNotNull(fetchCar);
        assertEquals(parkCar,fetchCar);
    }

    @Test
    public void should_return_ticket_when_packing_car_given_car() throws NoPositionException {
        //given
        Car car = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        //when
        ParkingTicket parkingTicket = parkingLotManager.parking(car);
        //then
        assertNotNull(parkingTicket);
    }

}