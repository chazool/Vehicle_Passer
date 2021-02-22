package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.PaymentMethodNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/services/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping
    public Response save(@RequestBody PaymentMethod paymentMethod) {
        paymentMethod = paymentMethodService.save(paymentMethod);
        return Response.success(paymentMethod);
    }

    @PutMapping
    public PaymentMethod update(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.update(paymentMethod);
    }

    @DeleteMapping(value = "/{id}")
    public PaymentMethod delete(@PathVariable int id) {
        return paymentMethodService.delete(id);
    }


    @GetMapping(value = "/{id}")
    public Response fetchById(@PathVariable int id) {
        try {
            PaymentMethod paymentMethod = paymentMethodService.findById(id);
            return Response.success(paymentMethod);
        } catch (PaymentMethodNotFoundException paymentMethodNotFoundException) {
            return Response.fail(paymentMethodNotFoundException.getMessage());
        }
    }

    @GetMapping(value = "/driver/{id}")
    public PaymentMethod fetchByDriverActivePaymentMethod(@PathVariable int id, @RequestParam(defaultValue = "yes") String isActive) {
        return isActive.equals("yes") ?
                paymentMethodService.findByDriver(id, true) : paymentMethodService.findByDriver(id, false);
    }

    @GetMapping
    public List<PaymentMethod> fetchAll() {
        return paymentMethodService.findAll();
    }


}
