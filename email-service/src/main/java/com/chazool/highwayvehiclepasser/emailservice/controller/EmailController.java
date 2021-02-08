package com.chazool.highwayvehiclepasser.emailservice.controller;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/services/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Email send(@RequestBody Email email) throws MessagingException {
        System.out.println("Email Controller .........................");
        return emailService.Send(email);
    }

}
