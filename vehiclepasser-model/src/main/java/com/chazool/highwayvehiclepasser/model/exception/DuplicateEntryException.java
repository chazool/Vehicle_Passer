package com.chazool.highwayvehiclepasser.model.exception;

import javax.swing.plaf.SpinnerUI;

public class DuplicateEntryException extends RuntimeException {


    public DuplicateEntryException(String message) {
        super(message);
    }


    public DuplicateEntryException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
