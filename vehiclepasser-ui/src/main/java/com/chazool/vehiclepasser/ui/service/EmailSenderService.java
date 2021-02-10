package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;

public interface EmailSenderService {

    Email send(Email email);
}
