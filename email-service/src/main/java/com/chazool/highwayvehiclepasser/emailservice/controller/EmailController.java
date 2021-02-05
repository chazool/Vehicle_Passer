package com.chazool.highwayvehiclepasser.emailservice.controller;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/services/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Email save(@RequestBody Email email) {


        //return emailService.save(email);

        return null;
    }

}
