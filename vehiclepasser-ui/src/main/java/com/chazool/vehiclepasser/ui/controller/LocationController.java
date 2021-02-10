package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/terminal/{locationId}")
    public List<Terminal> getTerminal(@PathVariable int locationId) {
        return locationService.findTerminal(locationId);
    }


}
