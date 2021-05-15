package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.exception.PaymentNotFoundException;
import com.chazool.highwayvehiclepasser.model.exception.ServiceDownException;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.paymentservice.config.AccessToken;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("services/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Response enter(@RequestBody Payment payment) {

        try {
            return Response.success(paymentService.enter(payment.getDriver(), payment.getEntranceTerminal()));
        } catch (ExecutionException e) {
            return Response.fail(e.getMessage());
        } catch (InterruptedException e) {
            return Response.fail(e.getMessage());
        } catch (ServiceDownException serviceDownException) {
            return Response.systemDown(serviceDownException.getMessage());
        }
    }

    @PutMapping
    public Response exit(@RequestBody Payment payment) {
        try {
            return Response.success(paymentService.exit(payment.getDriver(), payment.getExitTerminal()));
        } catch (PaymentNotFoundException paymentNotFoundException) {
            return Response.fail(paymentNotFoundException.getMessage());
        }
    }

    @GetMapping(value = "/driver/{driverId}")
    public Response findByDriver(@PathVariable int driverId) {
        try {
            Payment payment = paymentService.findDriverNotCompletePaymentByDriver(driverId);
            return Response.success(payment);
        } catch (PaymentNotFoundException paymentNotFoundException) {
            return Response.fail(paymentNotFoundException.getMessage());
        }
    }

    @GetMapping(value = "{id}")
    public Payment findById(@PathVariable int id) {

        return paymentService.findById(id);
    }

    @PutMapping(value = "/sendemail")
    public Payment isSendEmail(Payment payment) {
        return paymentService.isSendEmail(payment);
    }


    @GetMapping(value = "vehicle-counts")
    public Map<String, List> findVehicleCountByLocationAndEntranceDate(@RequestParam int location, @RequestParam String startDate, @RequestParam String endDate) {
        return paymentService.findVehicleCountByLocationAndDate(location, startDate, endDate);
    }

    @GetMapping(value = "entrance-vehicletype-count")
    public Map<Integer, Map> findEntranceVehicleTypeCountByLocationAndDate(@RequestParam int location, @RequestParam String startDate, @RequestParam String endDate) {
        return paymentService.findEntranceVehicleTypeCountByLocationAndDate(location, startDate, endDate);
    }

    @GetMapping(value = "exit-vehicletype-count")
    public Map<Integer, Map> findExitVehicleTypeCountByLocationAndDate(@RequestParam int location, @RequestParam String startDate, @RequestParam String endDate) {
        return paymentService.findExitVehicleTypeCountByLocationAndDate(location, startDate, endDate);
    }

}
