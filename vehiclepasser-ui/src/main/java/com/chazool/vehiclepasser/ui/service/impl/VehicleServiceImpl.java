package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.NotContextException;
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
    public Vehicle findById(int id) throws VehicleNotFoundException {
        Vehicle vehicle = restTemplate.getForObject("http://localhost:9191/services/vehicles/"+id, Vehicle.class);
        if (vehicle.getId() <= 0 || vehicle.getId() != id) {
            throw new VehicleNotFoundException("Invalid Vehicle");
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> finAll() {
        return null;
    }
}
