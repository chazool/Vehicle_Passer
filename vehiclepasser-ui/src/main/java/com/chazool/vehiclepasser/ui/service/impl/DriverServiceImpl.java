package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public Driver save(Driver driver) {
        driver = restTemplate.postForObject("http://driver/services/drivers/", driver, Driver.class);
        return driver;
    }

    @Override
    public Driver setActiveVehicle(int driverId, int activeVehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleService.findById(activeVehicleId);

        Driver driver = findById(driverId);
        driver.setActiveVehicle(vehicle.getId());

        restTemplate.put("http://driver/services/drivers/", driver, Driver.class);

        return driver;
    }

    @Override
    public Driver findById(int id) {
        return restTemplate.getForObject("http://driver/services/drivers/" + id, Driver.class);
    }

    @Override
    public Driver findByEmail(String email) {

        Driver driver = restTemplate.getForObject("http://driver/services/drivers/email/" + email, Driver.class);
        return driver;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }
}
