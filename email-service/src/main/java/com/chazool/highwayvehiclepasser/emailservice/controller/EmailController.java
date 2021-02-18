package com.chazool.highwayvehiclepasser.emailservice.controller;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;


@RestController
@RequestMapping("/services/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Response send(@RequestBody Email email) throws MessagingException {
        email = emailService.Send(email);
        return Response.success(email);
    }

    @GetMapping
    public Email getEmail() {
        return new Email();
    }

}
