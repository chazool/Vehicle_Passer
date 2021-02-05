package com.chazool.highwayvehiclepasser.emailservice.service;


import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService {

    Email Send(Email email) throws MessagingException;


}
