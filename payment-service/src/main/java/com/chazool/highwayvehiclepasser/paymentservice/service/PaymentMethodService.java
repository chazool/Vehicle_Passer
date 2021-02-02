package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

public interface PaymentMethodService {

    Response save(PaymentMethod paymentMethod);
    Response update(PaymentMethod paymentMethod);
    Response delete(PaymentMethod paymentMethod);
    Response findById(int id);
    Response findByDriver(int driverId,boolean isActive);



}
