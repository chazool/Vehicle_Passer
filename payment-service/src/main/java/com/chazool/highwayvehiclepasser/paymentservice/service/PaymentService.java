package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

public interface PaymentService {

    Response save(Payment payment);
    Response update(Payment payment);
    Response delete(Payment payment);
    Response findById(int id);
    Response findBuyDriver(int  driver);

}
