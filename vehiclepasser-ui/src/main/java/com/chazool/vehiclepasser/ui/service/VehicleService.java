package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;

import java.util.List;

public interface VehicleService {


    Vehicle save(Vehicle vehicle);

    Vehicle findById(int id);

    List<Vehicle> finAll();

    List<Vehicle> findByOwner();


}
