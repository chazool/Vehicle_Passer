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

    Map<String, List> findVehicleCountByExitTerminalAndDate(int location, String startDate, String endDate);

    Map<String, Map> findPaymentsByEntranceTerminalInAndDate(int location, String startDate, String endDate);


}
