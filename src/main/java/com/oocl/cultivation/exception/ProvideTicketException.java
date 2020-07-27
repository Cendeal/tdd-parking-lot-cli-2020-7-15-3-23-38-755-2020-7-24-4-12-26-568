package com.oocl.cultivation.exception;

public class ProvideTicketException extends Exception{

    public static final String PLEASE_PROVIDE_YOUR_PARKING_TICKET = "Please provide your parking ticket.";

    public ProvideTicketException(){
        super(PLEASE_PROVIDE_YOUR_PARKING_TICKET);
    }
}
