package com.chazool.vehiclepasser.ui.thread;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.vehiclepasser.ui.service.EmailSenderService;

public class EmailSender extends Thread {


    private Email email;

    private EmailSenderService emailSenderService;


    public EmailSender(Email email, EmailSenderService emailSenderService) {
        this.email = email;
        this.emailSenderService = emailSenderService;
    }


    @Override
    public void run() {
        emailSenderService.send(email);
    }
}
