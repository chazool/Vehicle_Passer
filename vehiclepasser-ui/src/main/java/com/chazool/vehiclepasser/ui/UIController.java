package com.chazool.vehiclepasser.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {


    @RequestMapping(value = "/")
    public String home() {
        return "index";
    }

    @RequestMapping(value = "register-drivers")
    public String driverRegistration() {
        return "driver-register";
    }

    @RequestMapping(value = "register-vehicles")
    public String vehicleRegistration() {
        return "vehicle-register";
    }


}
