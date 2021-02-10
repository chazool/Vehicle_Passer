package com.chazool.vehiclepasser.ui.controller;

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
    public String entrance(Model model, HttpSession httpSession) {
        // int id = (int) httpSession.getAttribute("loggedDriverId");
        model.addAttribute("payment", new Payment());
        model.addAttribute("locations", locationService.findAllLocations());
        return "entrance";
    }


    @PostMapping(value = "/entrance")
    public String entrancePay(@ModelAttribute Payment payment, Model model) {

        paymentService.enter(payment.getPaymentMethod(), payment.getEntranceTerminal());


        model.addAttribute("locations", locationService.findAllLocations());
        return "entrance";
    }


}
