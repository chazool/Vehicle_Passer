package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {


    @Autowired
    private LocationService locationService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/driver-register").permitAll()
                .anyRequest()
                .authenticated();
    }


    @RequestMapping(value = "/index")
    public String home(Model model) {

        //AccessToken.printAuth();
        model.addAttribute("username", AccessToken.getUsername());
        return "index";
    }

    @RequestMapping(value = "/")
    public String start() {
        return "start";
    }


    @RequestMapping(value = "/terminalConsole")
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
