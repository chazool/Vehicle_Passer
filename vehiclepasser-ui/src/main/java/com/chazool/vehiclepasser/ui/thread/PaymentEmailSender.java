package com.chazool.vehiclepasser.ui.thread;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.vehiclepasser.ui.service.EmailSenderService;
import com.chazool.vehiclepasser.ui.service.PaymentService;

public class PaymentEmailSender extends Thread {


    private String subject;
    private int driver;
    private int terminal;
    private PaymentService paymentService;


    public PaymentEmailSender(String subject, int driver, int terminal, PaymentService paymentService) {
        this.subject = subject;
        this.driver = driver;
        this.terminal = terminal;
        this.paymentService = paymentService;

    }


    @Override
    public void run() {
        paymentService.sendEmail(subject, driver, terminal);
    }
}
