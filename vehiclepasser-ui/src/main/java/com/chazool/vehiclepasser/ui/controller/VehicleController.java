package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import com.chazool.vehiclepasser.ui.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
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

        List<VehicleType> vehicleTypes = vehicleTypeService.findAll();
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("vehicleTypes", vehicleTypes);

        return "vehicle-register";
    }


    @PostMapping("vehicle-register")
    public String load(@ModelAttribute Vehicle vehicle, Model model, HttpServletRequest httpServletRequest) {
        vehicle.setOwnerId((int) httpServletRequest.getSession().getAttribute("loggedDriverId"));
        vehicle = vehicleService.save(vehicle);


        return "index";
    }


}
