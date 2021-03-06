package com.chazool.highwayvehiclepasser.paymentservice.controller;

import com.chazool.highwayvehiclepasser.model.exception.LowBalanceException;
import com.chazool.highwayvehiclepasser.model.exception.PaymentMethodNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.paymentservice.service.ReloadService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("services/recharge")
public class ReloadController {


    @Autowired
    private ReloadService reloadService;

    @PostMapping
    public Response save(@RequestBody Reload reload) {

        try {
            return Response.success(reloadService.save(reload));
        } catch (PaymentMethodNotFoundException paymentMethodNotFoundException) {
            return Response.fail(paymentMethodNotFoundException.getMessage());
        } catch (LowBalanceException lowBalanceException) {
            return Response.fail(lowBalanceException.getMessage());
        }
    }

    @GetMapping(value = "/card/{card}")
    public List<Reload> fetchByCards(@PathVariable int card) {
        return reloadService.findByCard(card);
    }


    @GetMapping
    public List<Map<String, String>> findByBetweenDateTime(@RequestParam String date1, @RequestParam String date2) {
        return reloadService.findByBetweenDateTime(date1, date2);
    }
}
