package com.chazool.highwayvehiclepasser.model.exception;

public class InvalidIdException extends RuntimeException {


    public InvalidIdException(String message) {
        super(message);
    }


    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
