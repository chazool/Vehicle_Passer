package com.chazool.highwayvehiclepasser.emailservice.service.impl;

import com.chazool.highwayvehiclepasser.emailservice.repository.EmailRepository;
import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private RestTemplate restTemplate;;


    @Override
    public Email save(Email email) {
        email.setActive(true);
        return emailRepository.save(email);
    }

    @Override
    public Email update(Email email) throws InvalidIdException {
        findById(email.getId());
        return emailRepository.save(email);
    }

    @Override
    public Email delete(int id) throws InvalidIdException {
        Email email = findById(id);
        email.setActive(false);
        return emailRepository.save(email);
    }

    @Override
    public Email findById(int id) throws InvalidIdException {
        Optional<Email> emailOptional = emailRepository.findById(id);

        if (emailOptional.isPresent()) {
            return emailOptional.get();
        } else {
            throw new InvalidIdException("Invalid Email Id");
        }
    }

    @Override
    public Email findByDriverId(int driver) {
        Optional<Email> email = emailRepository.findByDriverIdAndIsActive(driver, true);
        return email.isPresent() ? email.get() : null;
    }


    @Override
    public List<Email> findAll() {
        return emailRepository.findAll();
    }


  




}
