package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DriverService driverService;


    @Override
    public Vehicle save(Vehicle vehicle) {
        vehicle = restTemplate.postForObject("http://driver/services/vehicles/", vehicle, Vehicle.class);
        return vehicle;
    }

    @Override
    public Vehicle findById(int id) throws VehicleNotFoundException {
        Vehicle vehicle = restTemplate.getForObject("http://driver/services/vehicles/" + id, Vehicle.class);
        if (vehicle.getId() <= 0 || vehicle.getId() != id) {
            throw new VehicleNotFoundException("Invalid Vehicle");
        }

        return vehicle;
    }

    @Override
    public List<Vehicle> findByOwner() {
        Driver driver = driverService.findByUsername(AccessToken.getUsername());

        ResponseEntity<Vehicle[]> responseEntity = restTemplate.exchange(
                "http://driver/services/vehicles/owner/" + driver.getId()
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , Vehicle[].class);

        return Arrays.asList(responseEntity.getBody());
    }

    @Override
    public List<Vehicle> finAll() {
        return null;
    }
}
