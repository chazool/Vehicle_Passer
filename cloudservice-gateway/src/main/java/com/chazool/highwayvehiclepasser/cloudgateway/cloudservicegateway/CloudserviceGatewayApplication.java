package com.chazool.highwayvehiclepasser.cloudgateway.cloudservicegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudserviceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudserviceGatewayApplication.class, args);
    }

}
