package com.chazool.highwayvehiclepasser.cloudgateway.cloudservicegateway;

import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/driverFallBack")
    public Response driverServiceFallBack() {
        return Response.systemDown("Driver Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/emailFallback")
    public Response emailServiceFallBack() {
        return Response.systemDown("Email Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/paymentlFallback")
    public Response paymentServiceFallBack() {
        return Response.systemDown("Payment Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/transactionFallBack")
    public Response transactionServiceFallBack() {
        return Response.systemDown("Transaction Service is taking too long to respond or is down. Please try again later");
    }

}
