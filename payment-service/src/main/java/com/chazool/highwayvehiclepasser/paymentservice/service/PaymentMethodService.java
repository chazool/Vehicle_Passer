package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentMethodService {

    PaymentMethod save(PaymentMethod paymentMethod);

    PaymentMethod update(PaymentMethod paymentMethod);

    PaymentMethod delete(int id);

    PaymentMethod findById(int id);

    PaymentMethod findByDriver(int driverId, boolean isActive);

    List<PaymentMethod> findAll();

    PaymentMethod updateBalance(int id, BigDecimal amount);


}
