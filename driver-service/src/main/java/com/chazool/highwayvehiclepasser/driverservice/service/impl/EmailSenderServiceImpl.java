package com.chazool.highwayvehiclepasser.driverservice.service.impl;

import com.chazool.highwayvehiclepasser.driverservice.service.EmailSenderService;
import com.chazool.highwayvehiclepasser.driverservice.thread.EmailSender;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Response send(Email email, String authorization) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authorization);
        HttpEntity<Email> httpEntity = new HttpEntity<>(email, httpHeaders);

        ResponseEntity<Response> responseEntity = restTemplate
                .exchange("http://email/services/emails", HttpMethod.POST, httpEntity, Response.class);
        return responseEntity.getBody();
    }
}
