package com.chazool.vehiclepasser.ui.controller;

import com.chazool.vehiclepasser.ui.service.PaymentService;
import com.chazool.vehiclepasser.ui.service.ReloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ChartController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ReloadService reloadService;

    @GetMapping(value = "entranceandexit-vehicles/{location}")
    public Map findVehicleCountByLocationAndDate(@PathVariable int location) {
        return paymentService.findVehicleCountByLocationAndDate(location);
    }

    @GetMapping(value = "entrance-vehicletypes/{location}")
    public Map<String, Map> findEntranceVehicleTypeCountByLocationAndDate(@PathVariable int location) {
        return paymentService.findEntranceVehicleTypeCountByLocationAndDate(location);
    }

    @GetMapping(value = "exit-vehicletypes/{location}")
    public Map<String, Map> findExitVehicleTypeCountByLocationAndDate(@PathVariable int location) {
        return paymentService.findExitVehicleTypeCountByLocationAndDate(location);
    }

    @GetMapping(value = "find-recharge-summary")
    public List findByBetweenDateTime() {

        return reloadService.findByBetweenDateTime();
    }


}
