package com.oocl.cultivation.exception;

public class UnrecognizedException extends Exception{

    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket.";

    public UnrecognizedException(){
        super(UNRECOGNIZED_PARKING_TICKET);
    }
}
