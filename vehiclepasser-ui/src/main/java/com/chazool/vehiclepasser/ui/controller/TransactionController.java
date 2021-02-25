package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class TransactionController {

    @Autowired
    private LocationService locationService;


    @RequestMapping(value = "/terminalControll")
    @PreAuthorize("hasRole('ROLE_admin')")
    public String terminalConsole(Model model) {
        model.addAttribute("locations", locationService.findAllLocations());
        model.addAttribute("terminal", new Terminal());
        return "terminal-console";
    }

    @RequestMapping(value = "/transaction")
    public String transaction(@ModelAttribute Terminal terminal, Model model, HttpSession httpSession) {

        String page;
        httpSession.setAttribute("terminal", terminal);
        Payment payment = new Payment();

        if (terminal.getTerminalType() == '0') {
            payment.setEntranceTerminal(terminal.getId());
            page = "entrance";
        } else {
            payment.setExitTerminal(terminal.getId());
            page = "exit";
        }

        model.addAttribute("payment", payment);
        return page;
    }


}
