package com.chazool.highwayvehiclepasser.driverservice.service;


import com.chazool.highwayvehiclepasser.driverservice.thread.EmailSender;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface DriverService {

    Driver save(Driver driver, String authorization) throws ConstraintViolationException;

    Driver update(Driver driver);

    Driver delete(int id);

    Driver findById(int id);

    Driver findByDLicenseNo(String dLicenseNo);

    Driver findByEmail(String email);

    Driver findByUsername(String username);

    List<Driver> findByAll();

    void createCard(Driver driver, String accessToken);


}
