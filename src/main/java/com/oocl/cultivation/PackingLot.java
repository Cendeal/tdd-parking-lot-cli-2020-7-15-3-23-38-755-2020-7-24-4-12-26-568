package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class PackingLot {
    private Map<PackingTicket,Car> ground;
    public PackingLot(){
        this.ground = new HashMap<>();
    }
    public PackingTicket packACar(Car car){
        PackingTicket ticket = new PackingTicket();
        this.ground.put(ticket,car);
        return ticket;
    }

    public Car getCar(PackingTicket ticket){
        return this.ground.remove(ticket);
    }
}
