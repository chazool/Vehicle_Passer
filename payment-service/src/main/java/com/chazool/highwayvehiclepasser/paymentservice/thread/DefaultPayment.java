package com.chazool.highwayvehiclepasser.paymentservice.thread;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;

import java.math.BigDecimal;

public class DefaultPayment extends Thread {

    private PaymentService paymentService;
    private Payment payment;
    private String authorization;

    public DefaultPayment(Payment payment, PaymentService paymentService, String authorization) {
        this.payment = payment;
        this.paymentService = paymentService;
        this.authorization = authorization;
    }

    @Override
    public void run() {
        BigDecimal defaultVehicleCharge = paymentService.getVehicleCharge(payment.getVehicle(), authorization);
        payment.setCharge(defaultVehicleCharge);
        paymentService.update(payment);
    }
}
