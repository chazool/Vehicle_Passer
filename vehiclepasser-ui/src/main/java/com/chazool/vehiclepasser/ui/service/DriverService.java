package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverService {

    Driver save(Driver driver);

    Driver setActiveVehicle(int driverId, int activeVehicleId);

    Driver findById(int id);

    Driver findByEmail(String email);

    List<Driver> findAll();


}
