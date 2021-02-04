package com.chazool.highwayvehiclepasser.driverservice.service.impl;

import com.chazool.highwayvehiclepasser.driverservice.repository.VehicleRepository;
import com.chazool.highwayvehiclepasser.driverservice.service.VehicleService;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle update(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle delete(int id) {

        Vehicle vehicle = findById(id);
        vehicle.setActive(false);
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle findById(int id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.isPresent() ? vehicle.get() : null;
    }

    @Override
    public List<Vehicle> findByAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public List<Vehicle> findByOwnerId(int ownerId) {
        return vehicleRepository.findByOwnerId(ownerId);
    }
}
