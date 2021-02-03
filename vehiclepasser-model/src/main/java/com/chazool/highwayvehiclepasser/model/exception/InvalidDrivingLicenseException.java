package com.chazool.highwayvehiclepasser.model.exception;

public class InvalidDrivingLicenseException extends RuntimeException {

    public InvalidDrivingLicenseException(String message) {
        super(message);
    }

    public InvalidDrivingLicenseException(String message, Throwable cause) {
        super(message, cause);
    }


}
