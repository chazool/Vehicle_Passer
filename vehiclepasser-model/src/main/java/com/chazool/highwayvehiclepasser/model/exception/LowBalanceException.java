package com.chazool.highwayvehiclepasser.model.exception;

public class LowBalanceException extends RuntimeException {

    public LowBalanceException(String message) {
        super(message);
    }
}
