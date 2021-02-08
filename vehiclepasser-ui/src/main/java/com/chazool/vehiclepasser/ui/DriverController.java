package com.chazool.vehiclepasser.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DriverController {


    @PostMapping("/save")
    public String save(@ModelAttribute ) {

        return "index";
    }


}
