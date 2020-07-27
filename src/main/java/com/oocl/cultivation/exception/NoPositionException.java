package com.oocl.cultivation.exception;


public class NoPositionException extends Exception {

    public static final String NOT_ENOUGH_POSITION = "Not enough position.";

    public NoPositionException(){
        super(NOT_ENOUGH_POSITION);
    }
}
