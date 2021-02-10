package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.vehiclepasser.ui.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Email send(Email email) {
        return restTemplate.postForObject("http://localhost:9192/services/emails", email, Email.class);
    }
}
