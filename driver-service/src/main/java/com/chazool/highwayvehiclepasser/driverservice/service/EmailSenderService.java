package com.chazool.highwayvehiclepasser.driverservice.service;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@FunctionalInterface
public interface EmailSenderService {

    Response send(Email email, String authorization);
}
