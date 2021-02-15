package com.chazool.highwayvehiclepasser.driverservice.service.impl;

import com.chazool.highwayvehiclepasser.driverservice.service.EmailSenderService;
import com.chazool.highwayvehiclepasser.driverservice.thread.EmailSender;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Email send(Email email) {
        return restTemplate.postForObject("http://email/services/emails", email, Email.class);
    }
}
