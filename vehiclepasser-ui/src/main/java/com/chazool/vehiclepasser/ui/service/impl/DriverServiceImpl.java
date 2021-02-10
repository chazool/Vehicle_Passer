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
import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private VehicleService vehicleService;

    @Override
    public Driver save(Driver driver) {

        driver = restTemplate.postForObject("http://localhost:9191/services/drivers/", driver, Driver.class);
        System.out.println(driver);


        return driver;
    }

    @Override
    public Driver setActiveVehicle(int driverId, int activeVehicleId) throws VehicleNotFoundException {

        Vehicle vehicle = vehicleService.findById(activeVehicleId);

        Driver driver = restTemplate.getForObject("http://localhost:9191/services/drivers/" + driverId, Driver.class);
        driver.setActiveVehicle(vehicle.getId());

        restTemplate.put("http://localhost:9191/services/drivers/", driver, Driver.class);

        return driver;
    }

    @Override
    public Driver findById(int id) {
        return null;
    }

    @Override
    public Driver findByEmail(String email) {

        Driver driver = restTemplate.getForObject("http://localhost:9191/services/drivers/email/" + email, Driver.class);

        return driver;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }
}
