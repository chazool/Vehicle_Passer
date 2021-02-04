package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.paymentservice.service.ReloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReloadController {


    @Autowired
    private ReloadService reloadService;

    @PostMapping
    public Reload save(Reload reload) {
        return reloadService.save(reload);
    }

    @GetMapping(value = "/card")
    public List<Reload> fetchByCar(@PathVariable int card) {
        return reloadService.findByCard(card);
    }


}
