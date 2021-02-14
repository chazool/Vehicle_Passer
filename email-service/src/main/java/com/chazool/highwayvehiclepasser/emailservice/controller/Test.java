package com.chazool.highwayvehiclepasser.emailservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services/test")
public class Test {

    @GetMapping
    public String getTest() {
        System.out.println("Test........ 5");
        return "Test..." ;
    }


}
