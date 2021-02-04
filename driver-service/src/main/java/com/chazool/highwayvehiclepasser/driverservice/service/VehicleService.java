package com.chazool.highwayvehiclepasser.driverservice.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;

import java.util.List;

public interface VehicleService {


    Vehicle save(Vehicle vehicle);

    Vehicle update(Vehicle vehicle);

    Vehicle delete(int id);

    Vehicle findById(int id);

    List<Vehicle> findByAll();

    List<Vehicle> findByOwnerId(int ownerId);
}
