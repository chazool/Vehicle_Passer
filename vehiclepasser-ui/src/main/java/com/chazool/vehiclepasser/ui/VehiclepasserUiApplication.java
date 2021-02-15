package com.chazool.vehiclepasser.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VehiclepasserUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclepasserUiApplication.class, args);
    }

}
