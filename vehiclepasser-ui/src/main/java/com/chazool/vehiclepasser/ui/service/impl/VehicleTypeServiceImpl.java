package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import com.chazool.vehiclepasser.ui.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VehicleTypeServiceImpl implements VehicleTypeService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public VehicleType findById(int id) {
        return null;
    }

    @Override
    public List<VehicleType> findAll() {

        ResponseEntity<VehicleType[]> responseEntity = restTemplate.getForEntity("http://localhost:9194/services/vehicle-type/", VehicleType[].class);
        return Arrays.asList(responseEntity.getBody());
    }
}
