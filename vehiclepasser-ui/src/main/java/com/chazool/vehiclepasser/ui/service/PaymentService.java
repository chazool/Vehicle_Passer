package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface PaymentService {

    Response enter(int cardNo, int terminalId) throws ExecutionException, InterruptedException;

    Response exit(int cardNo, int terminalId);

    Payment isSendEmail(Payment payment);

    PaymentMethod findPaymentMethod(String username);

    void sendEmail(String subject, int driverId, int terminalId, String authorization);

    Map<String, List<Map<String, String>>> findVehicleCountByLocationAndDate(int location);

    Map<String, Map> findEntranceVehicleTypeCountByLocationAndDate(int location);

    Map<String, Map> findExitVehicleTypeCountByLocationAndDate(int location);


}
