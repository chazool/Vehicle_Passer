package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class ReloadServiceImpl implements ReloadService {


    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Response save(Reload reload) {

        ResponseEntity<Response> responseEntity = restTemplate.exchange(
                "http://payment/services/recharge"
                , HttpMethod.POST
                , AccessToken.getHttpEntity(reload)
                , Response.class
        );
        return responseEntity.getBody();
    }
}
