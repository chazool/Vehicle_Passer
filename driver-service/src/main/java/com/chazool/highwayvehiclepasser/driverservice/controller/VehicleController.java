package com.chazool.highwayvehiclepasser.driverservice.controller;

import com.chazool.highwayvehiclepasser.driverservice.service.VehicleService;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @PutMapping
    public Vehicle update(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }

    @DeleteMapping
    public Vehicle delete(@PathVariable int id) {
        return vehicleService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Vehicle findById(@PathVariable int id) {
        return vehicleService.findById(id);
    }

    @GetMapping
    public List<Vehicle> findByAll() {
        return vehicleService.findByAll();
    }

    @GetMapping(value = "/owner/{ownerId}")
    public List<Vehicle> findByOwnerId(@PathVariable int ownerId) {
        return vehicleService.findByOwnerId(ownerId);
    }


}
