package com.chazool.highwayvehiclepasser.transsaction.service;

import com.chazool.highwayvehiclepasser.model.transactionservice.Route;

import java.util.List;

public interface RouteService {


    Route save(Route route);

    Route update(Route route);

    Route delete(int id);

    Route findById(int id);

    List<Route> findByAll();

    List<Route> findByAll(int entrance,int exit);


}
