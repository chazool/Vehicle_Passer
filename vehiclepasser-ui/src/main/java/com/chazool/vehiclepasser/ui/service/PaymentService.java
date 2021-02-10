package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;

public interface PaymentService {

    Payment enter(int cardNo, int terminalId);

    Payment exit(int cardNo, int terminalId);

    Payment isSendEmail(Payment payment);


}
