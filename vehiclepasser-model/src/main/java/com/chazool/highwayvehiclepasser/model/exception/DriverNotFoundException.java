package com.chazool.highwayvehiclepasser.model.exception;

public class DriverNotFoundException extends RuntimeException{

    public DriverNotFoundException(String message){
        super(message);
    }

    public DriverNotFoundException(String message,Throwable cause){
        super(message,cause);
    }


}
