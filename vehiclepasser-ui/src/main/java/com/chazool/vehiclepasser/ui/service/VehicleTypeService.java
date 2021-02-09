package com.chazool.vehiclepasser.ui.service;


import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;

import java.util.List;

public interface VehicleTypeService {


    VehicleType findById(int id);

    List<VehicleType> findAll();


}
