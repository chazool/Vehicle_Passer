package com.chazool.highwayvehiclepasser.driverservice.service.impl;


import com.chazool.highwayvehiclepasser.driverservice.repository.DriverRepository;
import com.chazool.highwayvehiclepasser.driverservice.service.DriverService;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private DriverRepository driverRepository;


    @Override
    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Driver update(Driver driver) {



        return null;
    }

    @Override
    public Driver delete(Driver driver) {
        return null;
    }

    @Override
    public Driver findById(int id) {
        return null;
    }

    @Override
    public Driver findByDLicenseNo(String dLicenseNo) {
        return null;
    }

    @Override
    public List<Driver> findByAll() {
        return null;
    }
}
