package com.chazool.highwayvehiclepasser.emailservice.controller;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/emails")
public class EmailController {


    @Autowired
    private EmailService emailService;

}
