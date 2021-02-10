package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import com.chazool.highwayvehiclepasser.model.transactionservice.VehicleType;
import com.chazool.highwayvehiclepasser.paymentservice.repository.PaymentRepository;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentService;
import com.chazool.highwayvehiclepasser.paymentservice.thread.DefaultPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
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
        payment.setEntranceDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")));
        payment.setEntranceTerminal(entranceTerminal);

        payment = update(payment);

        DefaultPayment defaultPayment = new DefaultPayment(payment, this);
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
            payment.setExitDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")));
            payment.setExitTerminal(exitTerminal);
            payment.setComplete(true);
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
        return restTemplate.getForObject("http://localhost:9191/services/drivers/" + driverId, Driver.class);

    }

    private Route getRout(int entranceTerminal, int exitTerminal) {
        Route route = new Route();
        route.setEntrance(entranceTerminal);
        route.setExist(exitTerminal);

        HttpEntity<Route> httpEntity = new HttpEntity<>(route);

        ResponseEntity<Route> routeResponseEntity
                = restTemplate.exchange("http://localhost:9191/services/route/", HttpMethod.GET, httpEntity, Route.class);

        return routeResponseEntity.getBody();
    }

    public BigDecimal getVehicleCharge(int vehicleId) {
        Vehicle vehicle = restTemplate.getForObject("http://localhost:9191/services/vehicles/" + vehicleId, Vehicle.class);
        VehicleType vehicleType = restTemplate.getForObject("http://localhost:9194/services/vehicle-type/" + vehicle.getVehicleType(), VehicleType.class);
        return vehicleType.getCharge();
    }


}
