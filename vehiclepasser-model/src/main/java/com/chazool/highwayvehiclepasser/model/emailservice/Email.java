package com.chazool.highwayvehiclepasser.model.emailservice;

import lombok.Data;

@Data
public class Email {

    private String email;
    private String subject;
    private String message;
    private boolean isSend;

}
