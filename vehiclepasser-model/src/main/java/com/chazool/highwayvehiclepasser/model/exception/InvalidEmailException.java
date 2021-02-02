package com.chazool.highwayvehiclepasser.model.exception;


import org.omg.CORBA.DynAnyPackage.Invalid;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String message) {
        super(message);

    }


}
