package com.chazool.highwayvehiclepasser.model.exception;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;

public class EmailNotFoundException extends RuntimeException{

    public EmailNotFoundException(String message){
        super(message);
    }

    public EmailNotFoundException(String message,Throwable cause){
        super(message, cause);
    }


}
