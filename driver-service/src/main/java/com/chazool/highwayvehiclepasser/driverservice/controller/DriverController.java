package com.chazool.highwayvehiclepasser.driverservice.controller;


import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/services/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/register")
    public Response save(@RequestBody Driver driver, @RequestHeader Map<String, String> headers) {
        try {
            String authorization = headers.get("authorization");
            driver = driverService.save(driver, authorization);
            return Response.success(driver);
        } catch (InvalidEmailException invalidEmailException) {
            return Response.fail(invalidEmailException.getMessage());
        } catch (InvalidNameException invalidNameException) {
            return Response.fail(invalidNameException.getMessage());
        } catch (InvalidDrivingLicenseException invalidDrivingLicenseException) {
            return Response.fail(invalidDrivingLicenseException.getMessage());
        }
    }


    @PutMapping
    public Response update(@RequestBody Driver driver)
            throws InvalidIdException, InvalidEmailException, InvalidNameException, InvalidDrivingLicenseException {
        // System.out.println(driver1);
        try {
            //   Driver driver = new Driver();
            driver = driverService.update(driver);
            return Response.success(driver);
        } catch (InvalidEmailException invalidEmailException) {
            return Response.fail(invalidEmailException.getMessage());
        } catch (InvalidNameException invalidNameException) {
            return Response.fail(invalidNameException.getMessage());
        } catch (InvalidDrivingLicenseException invalidDrivingLicenseException) {
            return Response.fail(invalidDrivingLicenseException.getMessage());
        } catch (InvalidIdException invalidIdException) {
            return Response.fail(invalidIdException.getMessage());
        }
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

    @GetMapping(value = "/email/{email}")
    public Driver fetchByEmail(@PathVariable String email) throws InvalidIdException {
        return driverService.findByEmail(email);
    }

    @GetMapping(value = "/username/{username}")
    public Driver fetchByUsername(@PathVariable String username) {
        return driverService.findByUsername(username);
    }

    @GetMapping
    public List<Driver> fetchByAll() {




        return driverService.findByAll();
    }


}
