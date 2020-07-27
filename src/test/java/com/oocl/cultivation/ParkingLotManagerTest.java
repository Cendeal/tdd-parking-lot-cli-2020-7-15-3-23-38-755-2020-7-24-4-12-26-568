package com.oocl.cultivation;

import com.oocl.cultivation.exception.NoPositionException;
import com.oocl.cultivation.exception.ProvideTicketException;
import com.oocl.cultivation.exception.UnrecognizedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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


    @Test
    public void should_return_car_when_fetch_car_given_ticket() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        Car car = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingTicket parkingTicket = parkingLotManager.parking(car);
        //when
        Car fetchCar = parkingLotManager.fetch(parkingTicket);
        //then
        assertNotNull(fetchCar);
        assertEquals(car,fetchCar);
    }

    @Test
    void should_display_error_when_parking_car_given_more_than_10_cars() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        ParkingLot packingLot = new ParkingLot();
        SuperSmartParkingBoy packingBoy = new SuperSmartParkingBoy(packingLot);
        ParkingLotManager manager = new ParkingLotManager();
        manager.addParker(packingBoy);
        for(int i=0;i<10;i++){
            manager.specifyPackerParking(packingBoy,new Car());
        }
        //when
        NoPositionException exception = assertThrows(NoPositionException.class, () -> {
            manager.specifyPackerParking(packingBoy,new Car());
        });
        //then
        assertEquals("Not enough position.", exception.getMessage());
    }

    @Test
    public void should_return_car_when_fetch_car_given_ticket_give_has_many_parker() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        Car car = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingBoy parkingBoy = new ParkingBoy(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(new ParkingLot());
        parkingLotManager.addParker(parkingBoy,smartParkingBoy,superSmartParkingBoy);
        ParkingTicket ticket = parkingLotManager.specifyPackerParking(parkingBoy,car);
        //when
        Car fetchCar = parkingLotManager.fetch(ticket);
        //then
        assertNotNull(fetchCar);
        assertEquals(car,fetchCar);
    }

    @Test
    public void should_return_null_when_specifyPackerParking_given_ticket_give_parker_not_int_manage() throws NoPositionException, UnrecognizedException, ProvideTicketException {
        //given
        Car car = new Car();
        ParkingLotManager parkingLotManager = new ParkingLotManager();
        ParkingBoy parkingBoy = new ParkingBoy();
        //when
        ParkingTicket ticket = parkingLotManager.specifyPackerParking(parkingBoy,car);
        //then
        assertNull(ticket);
    }

}