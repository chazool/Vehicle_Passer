package com.chazool.highwayvehiclepasser.driverservice.thread;

import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;

public class PaymentCard extends Thread {

    private DriverService driverService;
    private Driver driver;

    public PaymentCard(Driver driver, DriverService driverService) {
        this.driver = driver;
        this.driverService = driverService;
    }


    @Override
    public void run() {
        driverService.createCard(driver);
    }

}
