package com.chazool.highwayvehiclepasser.driverservice.controller;


import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/services/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping
    public Driver save(@RequestBody Driver driver)
            throws InvalidEmailException, InvalidPasswordException, InvalidNameException, InvalidDrivingLicenseException {
        return driverService.save(driver);
    }


    @PutMapping
    public Driver update(@RequestBody Driver driver)
            throws InvalidIdException, InvalidEmailException, InvalidPasswordException, InvalidNameException, InvalidDrivingLicenseException {
        return driverService.update(driver);
    }

    @DeleteMapping(value = "/{id}")
    public Driver delete(@PathVariable int id) {
        return driverService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Driver fetchById(@PathVariable int id) throws InvalidIdException {
        return driverService.findById(id);
    }

    @GetMapping(value = "/dlicense/{dLNo}")
    public Driver fetchByDLicense(@PathVariable String dLNo) throws InvalidIdException {
        return driverService.findByDLicenseNo(dLNo);

    }

    @GetMapping
    public List<Driver> fetchByAll() {
        return driverService.findByAll();
    }


}
