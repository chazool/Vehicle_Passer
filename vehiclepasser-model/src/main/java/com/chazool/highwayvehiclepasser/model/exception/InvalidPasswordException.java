package com.chazool.highwayvehiclepasser.model.exception;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }


    public InvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
