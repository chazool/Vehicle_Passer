package com.chazool.highwayvehiclepasser.transsaction.service.impl;

import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.highwayvehiclepasser.transsaction.repository.VehicleTypeRepository;
import com.chazool.highwayvehiclepasser.transsaction.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;


    @Override
    public VehicleType save(VehicleType vehicleType) {
        vehicleType.setActive(true);
        return vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public VehicleType update(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public VehicleType delete(int id) {
        VehicleType vehicleType = findById(id);
        vehicleType.setActive(false);
        return vehicleTypeRepository.save(vehicleType);
    }

    @Override
    public VehicleType findById(int id) {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        return vehicleType.isPresent() ? vehicleType.get() : null;
    }

    @Override
    public List<VehicleType> findByAll() {
        return vehicleTypeRepository.findAll();
    }
}
