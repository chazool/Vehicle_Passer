package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import com.chazool.vehiclepasser.ui.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//@CrossOrigin(origins = "192.168.8.180:8181")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleTypeService vehicleTypeService;

    @GetMapping("vehicle-register")
    public String load(Model model) {

        model.addAttribute("vehicle", new Vehicle());
        setModel(model);
        return "vehicle-register";
    }

    @PostMapping("vehicle-register")
    public String save(@ModelAttribute Vehicle vehicle, Model model) {

        Response response = vehicleService.save(vehicle);
        model.addAttribute("response", response);
        setModel(model);
        if (response.isAction())
            model.addAttribute("vehicle", new Vehicle());
        else
            model.addAttribute("vehicle", vehicle);

        return "vehicle-register";
    }

    private void setModel(Model model) {
        List<VehicleType> vehicleTypes = vehicleTypeService.findAll();
        model.addAttribute("vehicleTypes", vehicleTypes);
        model.addAttribute("vehicles", vehicleService.findByOwner());
        model.addAttribute("username", AccessToken.getUsername());
    }

}
