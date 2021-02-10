package com.chazool.highwayvehiclepasser.driverservice.service;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@FunctionalInterface
public interface EmailSenderService {


    Email send(Email email);
}
