package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface PaymentService {


    Payment enter(int driverId, int entranceTerminal);

    Payment exit(int driverId, int exitTerminal);

    Payment findById(int id);

    Payment findDriverNotCompletePaymentByDriver(int driver);

    Payment isSendEmail(Payment payment);

    BigDecimal getVehicleCharge(int vehicleId, String authorization);

    Payment update(Payment payment);

    Map<String, List> findVehicleCountByLocationAndDate(int location, String startDate, String endDate);

    Map<Integer, Map> findEntranceVehicleTypeCountByLocationAndDate(int location, String startDate, String endDate);

    Map<Integer, Map> findExitVehicleTypeCountByLocationAndDate(int location, String startDate, String endDate);


}
