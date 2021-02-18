package com.chazool.highwayvehiclepasser.driverservice.service;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface VehicleService {


    Vehicle save(Vehicle vehicle,String authorization);

    Vehicle update(Vehicle vehicle);

    Vehicle delete(int id);

    Vehicle findById(int id);

    List<Vehicle> findByAll();

    List<Vehicle> findByOwnerId(int ownerId);



}
