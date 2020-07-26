package com.oocl.cultivation;

public class UnrecognizedException extends Exception{
    public UnrecognizedException(){
        super("Unrecognized parking ticket.");
    }
}
