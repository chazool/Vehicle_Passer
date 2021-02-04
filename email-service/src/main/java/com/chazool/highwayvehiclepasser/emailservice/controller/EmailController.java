package com.chazool.highwayvehiclepasser.emailservice.controller;

import com.chazool.highwayvehiclepasser.emailservice.service.EmailService;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.exception.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/emails")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Email save(@RequestBody Email email) {
        return emailService.save(email);
    }

    @PutMapping
    public Email update(@RequestBody Email email) throws InvalidIdException {
        return emailService.update(email);
    }

    @DeleteMapping(value = "/{id}")
    public Email delete(@PathVariable int id) throws InvalidIdException {
        return emailService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Email fetchById(@PathVariable int id) throws InvalidIdException {
        return emailService.findById(id);
    }

    @GetMapping(value = "/driver/{driverId}")
    public Email fetchByDriverId(@PathVariable int driverId) throws InvalidIdException {
        return emailService.findByDriverId(driverId);
    }

    @GetMapping
    public List<Email> fetchAll() {
        return emailService.findAll();
    }
}
