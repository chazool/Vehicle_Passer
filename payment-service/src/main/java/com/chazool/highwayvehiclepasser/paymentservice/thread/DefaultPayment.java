package com.chazool.highwayvehiclepasser.paymentservice.thread;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class DefaultPayment extends Thread {

    private PaymentService paymentService;
    private int paymentId;

    public DefaultPayment(int paymentId, PaymentService paymentService) {
        this.paymentId = paymentId;
        this.paymentService = paymentService;
    }

    @Override
    public void run() {
        Payment payment = paymentService.findById(this.paymentId);
        BigDecimal defaultVehicleCharge = paymentService.getVehicleCharge(payment.getVehicle());
        payment.setCharge(defaultVehicleCharge);
        paymentService.update(payment);

    }
}
