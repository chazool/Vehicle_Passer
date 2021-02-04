package com.chazool.highwayvehiclepasser.model.exception;


public class PaymentNotFoundException extends RuntimeException {

    public PaymentNotFoundException(String message) {
        super(message);
    }
}
