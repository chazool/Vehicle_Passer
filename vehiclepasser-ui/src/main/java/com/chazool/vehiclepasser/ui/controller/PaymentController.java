package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.exception.LowBalanceException;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.service.LocationService;
import com.chazool.vehiclepasser.ui.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {

    @Autowired
    private LocationService locationService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/entrance")
    public String entrance(Model model) {
        model.addAttribute("alert", false);
        setModel(model);
        return "entrance";
    }


    @PostMapping(value = "/entrance")

    public String entrance(@ModelAttribute Payment payment, Model model) {
        try {
            Response response = paymentService.enter(payment.getPaymentMethod(), payment.getEntranceTerminal());
            model.addAttribute("response", response);
        } catch (LowBalanceException lowBalanceException) {
            model.addAttribute("response", lowBalanceException.getMessage());
        }
        setModel(model);
        Payment payment1 = new Payment();
        payment1.setEntranceTerminal(payment.getEntranceTerminal());
        model.addAttribute("payment", payment1);
        return "entrance";
    }


    @RequestMapping(value = "exit")
    public String exit(Model model) {
        model.addAttribute("alert", false);
        setModel(model);
        return "exit";
    }

    @PostMapping(value = "exit")
    public String exit(@ModelAttribute Payment payment, Model model) {

        Response response = paymentService.exit(payment.getPaymentMethod(), payment.getExitTerminal());
        model.addAttribute("response", response);

        Payment payment1 = new Payment();
        payment1.setExitTerminal(payment.getEntranceTerminal());
        model.addAttribute("payment", payment1);

        setModel(model);
        return "exit";
    }


    private void setModel(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
    }


}
