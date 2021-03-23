package com.chazool.highwayvehiclepasser.transsaction.service.impl;

import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import com.chazool.highwayvehiclepasser.transsaction.repository.RouteRepository;
import com.chazool.highwayvehiclepasser.transsaction.service.LocationService;
import com.chazool.highwayvehiclepasser.transsaction.service.RouteService;
import com.chazool.highwayvehiclepasser.transsaction.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private TerminalService terminalService;


    @Override

    public Route save(Route route) {
        route.setActive(true);
        return routeRepository.save(route);
    }

    @Override
    public Route update(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public Route delete(int id) {
        Route route = findById(id);
        route.setActive(false);
        return routeRepository.save(route);
    }

    @Override
    public Route findById(int id) {
        Optional<Route> route = routeRepository.findById(id);
        return route.isPresent() ? route.get() : new Route();
    }

    @Override
    public List<Route> findByAll() {
        return routeRepository.findAll();
    }

    @Override
    public Route findByEntranceAndExist(int entrance, int exit) {

        return routeRepository.findByEntranceAndExist(
                terminalService.findById(entrance).getLocationId()
                , terminalService.findById(exit).getLocationId());
    }
}
