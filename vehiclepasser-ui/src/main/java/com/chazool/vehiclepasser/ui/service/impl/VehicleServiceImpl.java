package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle = restTemplate.postForObject("http://localhost:9191/services/vehicles/", vehicle, Vehicle.class);
        return vehicle;
    }

    @Override
    public Vehicle findById(int id) {
        return null;
    }

    @Override
    public List<Vehicle> finAll() {
        return null;
    }
}
