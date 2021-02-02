package com.chazool.highwayvehiclepasser.emailservice.service.impl;

import com.chazool.highwayvehiclepasser.emailservice.repository.EmailRepository;
import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;


    @Override
    public Response save(Email email) {
        return null;
    }

    @Override
    public Response update(Email email) {
        return null;
    }

    @Override
    public Response delete(int id) {
        return null;
    }

    @Override
    public Response findById(int id) {
        return null;
    }

    @Override
    public Response findByDriverId(int driver) {
        return null;
    }

    @Override
    public Response findByDriverId(int driver, boolean isActive) {
        return null;
    }

    @Override
    public Response findAll() {
        return null;
    }
}
