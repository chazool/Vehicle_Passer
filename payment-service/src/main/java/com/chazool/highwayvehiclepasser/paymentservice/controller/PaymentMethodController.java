package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public PaymentMethod save(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodService.save(paymentMethod);
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
    public PaymentMethod fetchById(@PathVariable int id) {
        return paymentMethodService.findById(id);
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
