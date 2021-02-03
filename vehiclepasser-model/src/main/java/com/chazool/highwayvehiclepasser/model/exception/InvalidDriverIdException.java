package com.chazool.highwayvehiclepasser.model.exception;

public class InvalidDriverIdException extends RuntimeException {


    public InvalidDriverIdException(String message) {
        super(message);
    }

    public InvalidDriverIdException(String message, Throwable cause) {
        super(message, cause);
    }


}
