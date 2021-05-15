package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface DriverService {

    Response save(Driver driver);

    Response update(Driver driver);

    Response setActiveVehicle(int activeVehicleId);

    Driver findById(int id) throws ExecutionException, InterruptedException;

    Driver findByEmail(String email);

    Driver findByUsername(String username);

    List<Driver> findAll();


}
