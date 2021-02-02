package com.chazool.highwayvehiclepasser.emailservice.service;


import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

public interface EmailService {

    Response save(Email email);
    Response update(Email email);
    Response delete(int id);
    Response findById(int id);
    Response findByDriverId(int driver);
    Response findByDriverId(int driver,boolean isActive);
    Response findAll();


}
