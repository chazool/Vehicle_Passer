package com.chazool.highwayvehiclepasser.transsaction.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import com.chazool.highwayvehiclepasser.transsaction.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping
    public Location update(@RequestBody Location location) {
        return locationService.update(location);

    }

    @DeleteMapping("/{id}")
    public Location delete(@PathVariable int id) {
        return locationService.delete(id);

    }

    @GetMapping
    public Location findById(@PathVariable int id) {
        return locationService.findById(id);

    }

    @GetMapping
    public List<Location> findByAll() {
        return locationService.findByAll();
    }


}
