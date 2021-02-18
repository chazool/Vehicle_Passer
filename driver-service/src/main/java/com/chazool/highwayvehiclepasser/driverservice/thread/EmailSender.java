package com.chazool.highwayvehiclepasser.driverservice.thread;

import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.driverservice.service.EmailSenderService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;


public class EmailSender extends Thread {

    private Email email;
    private EmailSenderService emailSenderService;
    private String authorization;

    public EmailSender(Email email, EmailSenderService emailSenderService, String authorization) {
        this.email = email;
        this.emailSenderService = emailSenderService;
        this.authorization = authorization;
    }

    @Override
    public void run() {
        emailSenderService.send(email, authorization);
    }


}
