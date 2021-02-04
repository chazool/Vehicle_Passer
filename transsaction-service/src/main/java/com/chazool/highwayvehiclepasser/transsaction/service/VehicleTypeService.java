package com.chazool.highwayvehiclepasser.transsaction.service;

import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.highwayvehiclepasser.transsaction.repository.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface VehicleTypeService {

    VehicleType save(VehicleType vehicleType);

    VehicleType update(VehicleType vehicleType);

    VehicleType delete(int id);

    VehicleType findById(int id);

    List<VehicleType> findByAll();


}
