package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

public interface PaymentService {

    Response enter(int cardNo, int terminalId);

    Response exit(int cardNo, int terminalId);

    Payment isSendEmail(Payment payment);

    void sendEmail(String subject, int driverId, int terminalId);


}
