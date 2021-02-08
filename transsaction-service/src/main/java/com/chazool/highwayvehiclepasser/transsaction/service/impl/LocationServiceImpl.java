package com.chazool.highwayvehiclepasser.transsaction.service.impl;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import com.chazool.highwayvehiclepasser.transsaction.repository.LocationRepository;
import com.chazool.highwayvehiclepasser.transsaction.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public Location save(Location location) {
        location.setActive(true);
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location delete(int id) {
        Location location = findById(id);
        location.setActive(false);
        return locationRepository.save(location);
    }

    @Override
    public Location findById(int id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        return optionalLocation.isPresent() ? optionalLocation.get() : new Location();
    }

    @Override
    public List<Location> findByAll() {
        return locationRepository.findAll();
    }
}
