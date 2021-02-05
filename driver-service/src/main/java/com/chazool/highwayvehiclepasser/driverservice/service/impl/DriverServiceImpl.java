package com.chazool.highwayvehiclepasser.driverservice.service.impl;


import com.chazool.highwayvehiclepasser.driverservice.repository.DriverRepository;
import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver save(Driver driver) throws InvalidEmailException, InvalidPasswordException, InvalidNameException,
            InvalidDrivingLicenseException {

        isValid(driver);
        driver.setActive(true);
        driver.setRegistrationDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")));

        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Driver driver) throws InvalidIdException, InvalidEmailException, InvalidPasswordException, InvalidNameException, InvalidDrivingLicenseException {

        findById(driver.getId());
        isValid(driver);
        return driverRepository.save(driver);
    }

    @Override
    public Driver delete(int id) throws InvalidIdException {

        Driver driver = findById(id);
        driver.setActive(false);
        return driverRepository.save(driver);
    }

    @Override
    public Driver findById(int id) throws InvalidIdException {
        Optional<Driver> driver = driverRepository.findById(id);
        return driver.isPresent() ? driver.get() : new Driver();

    }

    @Override
    public Driver findByDLicenseNo(String dLicenseNo) {
        Optional<Driver> driver = driverRepository.findBydLicenseNo(dLicenseNo);
        return driver.isPresent() ? driver.get() : new Driver();
    }

    @Override
    public List<Driver> findByAll() {
        return driverRepository.findAll();
    }


    private void isValid(Driver driver) throws InvalidPasswordException, InvalidEmailException {

        if (driver.getFName().trim().toString().equals(null) || driver.getFName().trim().equals(""))
            throw new InvalidNameException("Invalid First Name");
        if (driver.getLName().trim().toString().equals(null) || driver.getLName().trim().equals(""))
            throw new InvalidNameException("Invalid Last Name");
        if (Pattern.compile("^(.+)@(.+)$").matcher(driver.getEmail()).matches() == false) {
            throw new InvalidEmailException("Invalid Email Address");
        }
        if (driver.getPassword().trim().equals(null) || driver.getPassword().trim().equals("")
                || driver.getPassword().trim().length() <= 5) {
            throw new InvalidEmailException("Invalid Password");
        }
        if (driver.getDLicenseNo().trim().equals(null) || driver.getDLicenseNo().trim().equals("")
                || driver.getDLicenseNo().trim().length() < 9) {
            new InvalidDrivingLicenseException("Invalid Driving Licence ");
        }

    }


}
