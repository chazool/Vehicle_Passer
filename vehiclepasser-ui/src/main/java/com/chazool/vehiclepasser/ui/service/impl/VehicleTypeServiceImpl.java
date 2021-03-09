package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import com.chazool.vehiclepasser.ui.service.VehicleTypeService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Response> responseEntity = restTemplate
                .exchange("http://transsaction/services/vehicle-type/", HttpMethod.GET, httpEntity, Response.class);

        VehicleType[] vehicleTypes = new ObjectMapper().convertValue(responseEntity.getBody().getData(), VehicleType[].class);

        return Arrays.asList(vehicleTypes);

    }
}
