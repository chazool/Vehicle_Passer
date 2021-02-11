package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.exception.LowBalanceException;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.vehiclepasser.ui.service.LocationService;
import com.chazool.vehiclepasser.ui.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
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
            payment = paymentService.enter(payment.getPaymentMethod(), payment.getEntranceTerminal());
            if (payment.getId() > 0)
                model.addAttribute("alert", true);
        } catch (LowBalanceException lowBalanceException) {
            model.addAttribute("error", lowBalanceException.getMessage());
        }
        setModel(model);
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

        payment = paymentService.exit(payment.getPaymentMethod(), payment.getExitTerminal());

        if (payment.isComplete())
            model.addAttribute("alert", true);
        setModel(model);
        return "exit";
    }


    private void setModel(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("locations", locationService.findAllLocations());
    }


}
