package com.chazool.highwayvehiclepasser.transsaction.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.highwayvehiclepasser.transsaction.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/vehicle-type")
public class VehicleTypeController {
    @Autowired
    private VehicleTypeService vehicleTypeService;


    @PostMapping
    public VehicleType save(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.save(vehicleType);
    }

    @PutMapping
    public VehicleType update(@RequestBody VehicleType vehicleType) {
        return vehicleTypeService.update(vehicleType);
    }

    @DeleteMapping(value = "/{id}")
    public VehicleType delete(@PathVariable int id) {
        return vehicleTypeService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public VehicleType findById(@PathVariable int id) {
        return vehicleTypeService.findById(id);
    }

    @GetMapping
    public List<VehicleType> findByAll() {
        return vehicleTypeService.findByAll();
    }
}
