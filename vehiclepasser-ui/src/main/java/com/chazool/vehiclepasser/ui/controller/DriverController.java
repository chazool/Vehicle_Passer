package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.vehiclepasser.ui.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
//@CrossOrigin(origins = "192.168.8.180:8181")
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
    public String update(@ModelAttribute Driver driver, Model model, HttpServletRequest httpServletRequest) {
        String exceptionMessage = null;
        driver.setId((int) httpServletRequest.getSession().getAttribute("loggedDriverId"));
        try {
            driverService.setActiveVehicle(driver.getId(), driver.getActiveVehicle());
            return "index";
        } catch (VehicleNotFoundException vehicleNotFoundException) {
            exceptionMessage = vehicleNotFoundException.getMessage();
        }

        model.addAttribute("error", exceptionMessage);

        return "scan-vehicle";

    }

}
