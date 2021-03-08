package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.*;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
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
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    @Override
    public Map<String, List> findVehicleCountByLocationAndDate(int location, String startDate, String endDate) {

        List<Integer> entranceTerminals = getTerminalIds(location, 0);
        List<Integer> exitTerminals = getTerminalIds(location, 1);

        Map<String, List> vehicleCounts = new HashMap<>();
        vehicleCounts.put("entrance", paymentRepository.findVehicleCountByEntranceTerminalAndDate(entranceTerminals, startDate, endDate));
        vehicleCounts.put("exit", paymentRepository.findVehicleCountByExitTerminalAndDate(exitTerminals, startDate, endDate));

        return vehicleCounts;
    }

    @Override
    public Map<Integer, Map> findEntranceVehicleTypeCountByLocationAndDate(int location, String startDate, String endDate) {
        List<Integer> entranceTerminals = getTerminalIds(location, 0);
        List<VehicleType> vehicleTypes = getVehicleTypes();

        List<Payment> entrancePayments = paymentRepository.findPaymentsByEntranceTerminalInAndDate(entranceTerminals, startDate, endDate);
        Map<Integer, Map> entranceVehicleTypeCount = new HashMap<>();

        vehicleTypes.forEach(vehicleType -> {
            Map<LocalDate, Long> counting = entrancePayments.stream().filter(payment -> {
                return findVehicle(payment.getVehicle(), AccessToken.getHttpEntity())
                        .getVehicleType() == vehicleType.getId();
            }).collect(Collectors.groupingBy(payment -> payment.getEntranceDate().toLocalDate(), Collectors.counting()));

            entranceVehicleTypeCount.put(vehicleType.getId(), counting);
        });

        return entranceVehicleTypeCount;
    }

    @Override
    public Map<Integer, Map> findExitVehicleTypeCountByLocationAndDate(int location, String startDate, String endDate) {
        List<Integer> exitTerminals = getTerminalIds(location, 1);
        List<VehicleType> vehicleTypes = getVehicleTypes();

        List<Payment> exitPayments = paymentRepository.findPaymentsByExitTerminalInAndDate(exitTerminals, startDate, endDate);

        Map<Integer, Map> exitVehicleTypeCount = new HashMap<>();

        vehicleTypes.forEach(vehicleType ->
        {
            Map<LocalDate, Long> counting = exitPayments.stream().filter(payment -> {
                return findVehicle(payment.getVehicle(), AccessToken.getHttpEntity())
                        .getVehicleType() == vehicleType.getId();
            }).collect(Collectors.groupingBy(payment -> payment.getExitDate().toLocalDate(), Collectors.counting()));
            exitVehicleTypeCount.put(vehicleType.getId(), counting);
        });

        return exitVehicleTypeCount;
    }

    public List<VehicleType> getVehicleTypes() {
        ResponseEntity<Response> responseEntity = restTemplate
                .exchange("http://transsaction/services/vehicle-type/", HttpMethod.GET, AccessToken.getHttpEntity(), Response.class);

        VehicleType[] vehicleTypes = new ObjectMapper().convertValue(responseEntity.getBody().getData(), VehicleType[].class);

        return Arrays.asList(vehicleTypes);
    }

    private List<Terminal> findTerminal(int location, int terminalType) {
        ResponseEntity<Terminal[]> terminals = restTemplate.exchange(
                "http://transsaction/services/terminal?location=" + location + "&terminalType=" + terminalType
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , Terminal[].class);
        return Arrays.asList(terminals.getBody());
    }

    private List<Integer> getTerminalIds(int location, int terminalType) {
        return findTerminal(location, terminalType).stream().map(terminal -> terminal.getId()).collect(Collectors.toList());
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

    private Vehicle findVehicle(int vehicleId, HttpEntity httpEntity) {
        ResponseEntity<Response> vehicleResponseEntity = restTemplate.exchange(
                "http://driver/services/vehicles/" + vehicleId
                , HttpMethod.GET
                , httpEntity
                , Response.class);

        return new ObjectMapper().convertValue(vehicleResponseEntity.getBody().getData(), Vehicle.class);
    }

    public BigDecimal getVehicleCharge(int vehicleId, String authorization) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", authorization);
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);

        Vehicle vehicle = findVehicle(vehicleId, httpEntity);


        ResponseEntity<VehicleType> vehicleTypeResponseEntity = restTemplate.exchange(
                "http://transsaction/services/vehicle-type/" + vehicle.getVehicleType()
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , VehicleType.class);

        return vehicleTypeResponseEntity.getBody().getCharge();

    }


}
