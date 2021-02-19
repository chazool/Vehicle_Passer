package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;

public interface VehicleService {


    Response save(Vehicle vehicle);

    Vehicle findById(int id);

    List<Vehicle> finAll();

    List<Vehicle> findByOwner();


}
