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

    @GetMapping("/driver-profile")
    public String driverProfile(Model model, HttpServletRequest httpServletRequest) {
        Driver driver = driverService.findById((int) httpServletRequest.getSession().getAttribute("loggedDriverId"));

        model.addAttribute("driver", driver);
        return "driver-profile";
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

    @PostMapping("/driver-profile")
    public String updateDriverProfile(@ModelAttribute Driver driver, Model model, HttpServletRequest httpServletRequest) {
        int driverId = (int) httpServletRequest.getSession().getAttribute("loggedDriverId");
        Driver oldDriver = driverService.findById(driverId);

        driver.setId(driverId);
        driver.setPassword(oldDriver.getPassword());
        driver.setActive(true);
        driverService.update(driver);

        driver = driverService.findById(driverId);

        model.addAttribute("driver", driver);
        return "driver-profile";
    }

}
