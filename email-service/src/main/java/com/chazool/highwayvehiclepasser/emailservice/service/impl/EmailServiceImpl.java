package com.chazool.highwayvehiclepasser.emailservice.service.impl;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public Email Send(Email email) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo(email.getEmail());
        mimeMessageHelper.setText(email.getMessage(), true);
        mimeMessageHelper.setSubject(email.getSubject());
        javaMailSender.send(mimeMessage);

        email.setSend(true);
        return email;
    }
}
