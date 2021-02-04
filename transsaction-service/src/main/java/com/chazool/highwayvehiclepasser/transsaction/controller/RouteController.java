package com.chazool.highwayvehiclepasser.transsaction.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import com.chazool.highwayvehiclepasser.transsaction.repository.RouteRepository;
import com.chazool.highwayvehiclepasser.transsaction.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/routs")
public class RouteController {

    @Autowired
    private RouteService routeService;

    public Route save(@RequestBody Route route) {
        return routeService.save(route);
    }

    public Route update(@RequestBody Route route) {
        return routeService.update(route);
    }

    public Route delete(@PathVariable int id) {
        return routeService.delete(id);
    }

    @GetMapping
    public Route findByEntranceAndExist(@RequestBody Route route) {
        return routeService.findByEntranceAndExist(route.getEntrance(), route.getExist());
    }
}
