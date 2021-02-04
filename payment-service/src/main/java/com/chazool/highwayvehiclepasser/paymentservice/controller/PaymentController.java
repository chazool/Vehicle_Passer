package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("services/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment enter(@RequestBody Payment payment) {
        return paymentService.enter(payment.getDriver(), payment.getEntranceTerminal());
    }

    @PutMapping
    Payment exit(@RequestBody Payment payment) {
        return paymentService.enter(payment.getDriver(), payment.getExitTerminal());
    }

    @PutMapping(value = "/sendemail")
    public Payment isSendEmail(Payment payment) {
        return paymentService.isSendEmail(payment);
    }


}
