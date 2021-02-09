package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.InvalidEmailException;
import com.chazool.highwayvehiclepasser.model.exception.InvalidPasswordException;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DriverService driverService;

    @Override
    public boolean login(String username, String password) throws InvalidEmailException, InvalidPasswordException {

        Driver driver = driverService.findByEmail(username);
        if (driver.getEmail() == null) {
            throw new InvalidEmailException("Invalid User Name");
        } else {
            if (driver.getPassword().equals(password)) {
                return true;
            } else {
                throw new InvalidPasswordException("Invalid Password");
            }
        }


    }
}
