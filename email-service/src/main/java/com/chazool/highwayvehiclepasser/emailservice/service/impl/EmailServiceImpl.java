package com.chazool.highwayvehiclepasser.emailservice.service.impl;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {


    @Override
    public Email Send(Email email) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 465);
        properties.put("mail.smtp.user", "chazooltopup@gmail.com");
        properties.put("mail.smtp.password", "hellosrilanka");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.debug", "true");
        properties.put("mail.smtp.socketFactory.port", 465);
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getDefaultInstance(properties, null);
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        //message
        message.setText("Email Send");
        //subject
        message.setSubject("Password for your account");
        //
        message.setFrom(new InternetAddress(properties.get("mail.smtp.user").toString()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("chazoolkaweesha@gmail.com"));
        message.saveChanges();
        Transport transport = session.getTransport("smtp");

        transport.connect(properties.get("mail.smtp.host").toString(), properties.get("mail.smtp.user").toString(), properties.get("mail.smtp.password").toString());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        System.out.println("Your password mailed to you");


        return null;
    }
}
