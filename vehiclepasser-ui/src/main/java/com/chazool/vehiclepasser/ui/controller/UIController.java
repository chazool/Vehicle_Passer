package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UIController {

    //@RequestMapping(value = "/index")
    @RequestMapping(value = "/")
    public String home(HttpSession httpSession) {
        // int id = (int) httpSession.getAttribute("loggedDriverId");
        return "index";
    }


}
