package com.oocl.cultivation.exception;


public class NoPositionException extends Exception {
    public NoPositionException(){
        super("Not enough position.");
    }
}
