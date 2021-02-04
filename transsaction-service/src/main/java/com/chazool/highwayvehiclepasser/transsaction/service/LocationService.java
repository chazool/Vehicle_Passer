package com.chazool.highwayvehiclepasser.transsaction.service;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;

import java.util.List;

public interface LocationService {


    Location save(Location location);

    Location update(Location location);

    Location delete(int id);

    Location findById(int id);

    List<Location> findByAll();


}
