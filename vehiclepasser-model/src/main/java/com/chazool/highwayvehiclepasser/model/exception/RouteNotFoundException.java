package com.chazool.highwayvehiclepasser.model.exception;

import java.util.function.Supplier;

public class RouteNotFoundException extends RuntimeException {

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
