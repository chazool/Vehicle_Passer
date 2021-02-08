package com.chazool.highwayvehiclepasser.driverservice.thread;

import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;


public class EmailSender extends Thread {


    private Email email;
    private DriverService driverService;


    public EmailSender(Email email, DriverService driverService) {
        this.email = email;
        this.driverService = driverService;
    }


    @Override
    public void run() {
        //Email email = driverService.sendEmail(this.email);
    }


}
