package com.chazool.highwayvehiclepasser.driverservice.service;


import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;

public interface DriverService {

    Driver save(Driver driver);

    Driver update(Driver driver);

    Driver delete(Driver driver);

    Driver findById(int id);

    Driver findByDLicenseNo(String dLicenseNo);

    List<Driver> findByAll();

}
