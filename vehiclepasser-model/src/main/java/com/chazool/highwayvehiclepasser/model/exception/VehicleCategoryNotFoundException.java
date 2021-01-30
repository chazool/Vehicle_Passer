package com.chazool.highwayvehiclepasser.model.exception;

public class VehicleCategoryNotFoundException extends RuntimeException{

    public VehicleCategoryNotFoundException(String message){
        super(message);
    }
    public VehicleCategoryNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
}
