package com.chazool.vehiclepasser.ui.hystrix;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class DriverHystrixCommand extends HystrixCommand<Driver> {


    private RestTemplate restTemplate;
    private int driverId;

    public DriverHystrixCommand(RestTemplate restTemplate, int driverId) {

        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.restTemplate = restTemplate;
        this.driverId = driverId;
    }

    @Override
    protected Driver run() throws Exception {

        ResponseEntity<Driver> responseEntity = restTemplate.exchange(
                "http://driver/services/drivers/" + driverId
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , Driver.class);
        System.out.println(AccessToken.getHttpEntity());
        return responseEntity.getBody();
    }

    @Override
    protected Driver getFallback() {
        System.out.println("hit on fallback");
        return new Driver();
    }


}
