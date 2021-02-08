package com.chazool.highwayvehiclepasser.emailservice.service.impl;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public Email Send(Email email) throws MessagingException {

        SimpleMailMessage msg = new SimpleMailMessage();

        msg.setTo(email.getEmail());
        msg.setSubject(email.getSubject());
        msg.setText(email.getMessage());

        javaMailSender.send(msg);

        email.setSend(true);
        return email;
    }
}
