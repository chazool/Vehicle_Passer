package com.chazool.highwayvehiclepasser.transsaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EntityScan(basePackages = {"com.chazool.highwayvehiclepasser.model.transactionservice"
        , "com.chazool.highwayvehiclepasser.model.responsehandle"})
@EnableEurekaClient
@EnableResourceServer
public class TranssactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranssactionServiceApplication.class, args);
    }

}
