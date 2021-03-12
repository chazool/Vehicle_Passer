package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/terminal")
    public List<Terminal> getTerminal(@RequestParam int location, @RequestParam int terminalType) {
        return locationService.findTerminal(location, terminalType);
    }


}
