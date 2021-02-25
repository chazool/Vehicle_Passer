package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.highwayvehiclepasser.paymentservice.config.AccessToken;
import com.chazool.highwayvehiclepasser.paymentservice.repository.PaymentRepository;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import com.chazool.highwayvehiclepasser.paymentservice.thread.DefaultPayment;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Payment enter(int driverId, int entranceTerminal) {

        Driver driver = getDriver(driverId);

        Payment payment = new Payment();
        payment.setDriver(driver.getId());
        payment.setVehicle(driver.getActiveVehicle());
        payment.setEntranceDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
        payment.setEntranceTerminal(entranceTerminal);

        payment = update(payment);

        DefaultPayment defaultPayment = new DefaultPayment(payment, this, AccessToken.getAccessToken());
        defaultPayment.start();

        return payment;
    }

    @Override
    public Payment exit(int driverId, int exitTerminal) throws PaymentNotFoundException {
        Optional<Payment> optionalPayment = paymentRepository.findByDriverAndIsComplete(driverId, false);

        if (optionalPayment.isPresent()) {
            Payment payment = optionalPayment.get();
            PaymentMethod paymentMethod = paymentMethodService.findByDriver(payment.getDriver(), true);

            Route route = getRout(payment.getEntranceTerminal(), exitTerminal);

            //Update Object
            payment.setCharge(payment.getCharge().add(route.getFee()));
            payment.setPaymentMethod(paymentMethod.getId());
            payment.setExitDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
            payment.setExitTerminal(exitTerminal);
            payment.setComplete(true);

            //Update card Balance
            paymentMethod.setBalanceAmount(paymentMethod.getBalanceAmount().subtract(payment.getCharge()));
            paymentMethodService.update(paymentMethod);

            return update(payment);
        } else
            throw new PaymentNotFoundException("Payment not Exit");

    }

    @Override
    public Payment findById(int id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.isPresent() ? payment.get() : new Payment();
    }

    @Override
    public Payment findDriverNotCompletePaymentByDriver(int driver) throws PaymentNotFoundException {
        Optional<Payment> payment = paymentRepository.findByDriverAndIsComplete(driver, false);
        if (payment.isPresent())
            return payment.get();
        else
            throw new PaymentNotFoundException("Payment Not Found.!");
    }

    @Override
    public Payment isSendEmail(Payment payment) {
        payment.setSendEmail(true);
        return update(payment);
    }

    public synchronized Payment update(Payment payment) {
        return paymentRepository.save(payment);
    }

    public synchronized Payment findByDriverAndIsComplete(int driver, boolean isComplete) {
        Optional<Payment> payment = paymentRepository.findByDriverAndIsComplete(driver, false);
        return payment.isPresent() ? payment.get() : new Payment();
    }


    private Driver getDriver(int driverId) throws DriverNotFoundException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Driver> responseEntity = restTemplate.exchange("http://driver/services/drivers/" + driverId
                , HttpMethod.GET
                , httpEntity
                , Driver.class);

        return responseEntity.getBody();
    }

    private Route getRout(int entranceTerminal, int exitTerminal) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", AccessToken.getAccessToken());

       /* Route route = new Route();
        route.setEntrance(entranceTerminal);
        route.setExist(exitTerminal);*/

        HttpEntity<Route> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Route> routeResponseEntity
                = restTemplate.exchange(
                "http://transsaction/services/routs?entrance=" + entranceTerminal + "&exit=" + exitTerminal
                , HttpMethod.GET
                , httpEntity
                , Route.class
                , entranceTerminal, exitTerminal);

        return routeResponseEntity.getBody();
    }

    public BigDecimal getVehicleCharge(int vehicleId, String authorization) {

        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println(authorization);
        httpHeaders.add("Authorization", authorization);

        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Response> vehicleResponseEntity = restTemplate.exchange(
                "http://driver/services/vehicles/" + vehicleId
                , HttpMethod.GET
                , httpEntity
                , Response.class);

        Vehicle vehicle = new ObjectMapper().convertValue(vehicleResponseEntity.getBody().getData(), Vehicle.class);

        ResponseEntity<VehicleType> vehicleTypeResponseEntity = restTemplate.exchange(
                "http://transsaction/services/vehicle-type/" + vehicle.getVehicleType()
                , HttpMethod.GET
                , httpEntity
                , VehicleType.class);

        return vehicleTypeResponseEntity.getBody().getCharge();

    }


}
