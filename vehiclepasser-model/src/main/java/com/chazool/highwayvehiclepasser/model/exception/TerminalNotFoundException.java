package com.chazool.highwayvehiclepasser.model.exception;

public class TerminalNotFoundException extends RuntimeException {

    public TerminalNotFoundException(String message){
        super(message);
    }

    public TerminalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
