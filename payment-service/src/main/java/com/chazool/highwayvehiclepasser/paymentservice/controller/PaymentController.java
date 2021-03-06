package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.exception.PaymentNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.paymentservice.config.AccessToken;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("services/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Response enter(@RequestBody Payment payment) {
        System.out.println(AccessToken.getAccessToken());

        return Response.success(paymentService.enter(payment.getDriver(), payment.getEntranceTerminal()));
    }

    @PutMapping
    public Response exit(@RequestBody Payment payment) {
        try {
            //    Payment payment = new Payment();
            return Response.success(paymentService.exit(payment.getDriver(), payment.getExitTerminal()));
        } catch (PaymentNotFoundException paymentNotFoundException) {
            return Response.fail(paymentNotFoundException.getMessage());
        }
    }

    @GetMapping(value = "/driver/{driverId}")
    public Response findByDriver(@PathVariable int driverId) {
        try {
            Payment payment = paymentService.findDriverNotCompletePaymentByDriver(driverId);
            return Response.success(payment);
        } catch (PaymentNotFoundException paymentNotFoundException) {
            return Response.fail(paymentNotFoundException.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public Payment findById(@PathVariable int id) {

        return paymentService.findById(id);
    }

    @PutMapping(value = "/sendemail")
    public Payment isSendEmail(Payment payment) {
        return paymentService.isSendEmail(payment);
    }


    @GetMapping
    public Map<String, List> findByLocationAndEntranceDate(@RequestParam int location, @RequestParam String startDate, @RequestParam String endDate) {
        return paymentService.findByLocationAndEntranceDate(location, startDate, endDate);
    }



}
