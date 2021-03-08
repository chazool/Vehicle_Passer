package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {

    @Autowired
    private LocationService locationService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private VehicleService vehicleService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/driver-register").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

    }


    @RequestMapping(value = "/index")
    public String home(Authentication authentication, Model model) {

        model.addAttribute("username", AccessToken.getUsername());

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_admin"))) {
            model.addAttribute("locations", locationService.findAllLocations());
            model.addAttribute("terminal", new Terminal());
            // return "redirect:driver";
            //  return "terminal-console";

            //
            return "index-admin";
        } else {
            PaymentMethod paymentMethod = paymentService.findPaymentMethod(AccessToken.getUsername());

            Driver driver = driverService.findByUsername(AccessToken.getUsername());
            Response vehicleResponse = vehicleService.findById(driver.getActiveVehicle());

            model.addAttribute("vehicle", new ObjectMapper().convertValue(vehicleResponse.getData(), Vehicle.class));

            model.addAttribute("cardNo", "0000 0000 0000 " + paymentMethod.getId());
            model.addAttribute("cardBalance", "LKR " + paymentMethod.getBalanceAmount());
            model.addAttribute("cardIssueDate", paymentMethod.getIssueDate().getDayOfMonth()
                    + "/" + paymentMethod.getIssueDate().getMonthValue()
                    + "/" + paymentMethod.getIssueDate().getYear());


            return "index";

            // return "index-operator";
        }
    }

    @RequestMapping(value = "/")
    public String start() {
        return "start";
    }


}
