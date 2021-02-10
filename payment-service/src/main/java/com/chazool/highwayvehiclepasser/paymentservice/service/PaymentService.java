package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentService {


    Payment enter(int driverId, int entranceTerminal);

    Payment exit(int driverId, int exitTerminal);

    Payment findById(int id);

    Payment findDriverNotCompletePaymentByDriver(int driver);

    Payment isSendEmail(Payment payment);

    BigDecimal getVehicleCharge(int vehicleId);

    Payment update(Payment payment);

}
