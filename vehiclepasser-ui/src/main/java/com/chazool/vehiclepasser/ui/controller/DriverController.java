package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.vehiclepasser.ui.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/driver-register")
    public String load(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver-register";
    }

    @GetMapping("/driver-activeVehicle")
    public String scanVehicle(Model model) {
        model.addAttribute("driver", new Driver());
        return "scan-vehicle";
    }

    @PostMapping("/driver-register")
    public String save(@ModelAttribute Driver driver, Model model) {
        driver = driverService.save(driver);
        return "index";
    }

    @PostMapping("/driver-activeVehicle")
    public String update(@ModelAttribute Driver driver, Model model) {
        driver.setId(33);
        driver = driverService.setActiveVehicle(driver.getId(), driver.getActiveVehicle());
        return "index";
    }

}
