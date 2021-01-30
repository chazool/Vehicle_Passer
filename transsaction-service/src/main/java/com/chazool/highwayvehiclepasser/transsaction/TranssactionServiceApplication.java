package com.chazool.highwayvehiclepasser.transsaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.chazool.highwayvehiclepasser.model.transactionservice")
public class TranssactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TranssactionServiceApplication.class, args);
    }

}
