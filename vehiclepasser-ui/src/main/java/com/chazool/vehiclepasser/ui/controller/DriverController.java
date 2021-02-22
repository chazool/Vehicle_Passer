package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Controller
@EnableOAuth2Sso
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    RestTemplate restTemplate;

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
    public String driverProfile(Model model) {
        Driver driver = driverService.findByUsername(AccessToken.getUsername());
        model.addAttribute("driver", driver);
        model.addAttribute("username", AccessToken.getUsername());

        return "driver-profile";
    }

    @PostMapping("/driver-register")
    public String save(@ModelAttribute Driver driver, Model model) {

        Response response = driverService.save(driver);
        model.addAttribute("response", response);
        if (response.isAction())
            model.addAttribute("driver", new Driver());
        else
            model.addAttribute("driver", driver);

        return "driver-register";
    }


    @PostMapping("/driver-activeVehicle")
    public String update(@ModelAttribute Driver driver, Model model) {

        Response response = driverService.setActiveVehicle(driver.getActiveVehicle());
        model.addAttribute("response", response);
        if (response.isAction())
            model.addAttribute("driver", new Driver());
        else
            model.addAttribute("driver", driver);

        return "scan-vehicle";
    }

    @PostMapping("/driver-profile")
    public String updateDriverProfile(@ModelAttribute Driver driver, Model model) {

        Response response = driverService.update(driver);
        model.addAttribute("response", response);
        model.addAttribute("user", AccessToken.getUsername());
        model.addAttribute("driver", driver);

        return "driver-profile";
    }

}
