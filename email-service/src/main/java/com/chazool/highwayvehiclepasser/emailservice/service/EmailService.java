package com.chazool.highwayvehiclepasser.emailservice.service;


import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;

public interface EmailService {

    Email save(Email email);

    Email update(Email email);

    Email delete(int id);

    Email findById(int id);

    Email findByDriverId(int driver);

    List<Email> findAll();


}
