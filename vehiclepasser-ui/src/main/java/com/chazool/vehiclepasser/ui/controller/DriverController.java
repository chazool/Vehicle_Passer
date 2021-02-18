package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.vehiclepasser.ui.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Controller
@EnableOAuth2Sso
public class DriverController {

    @Autowired
    private DriverService driverService;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/driver-register")
    public String load(Model model) {
        model.addAttribute("driver", new Driver());
        return "driver-register";
    }

    @GetMapping("/driver-activeVehicle")
    public String scanVehicle(Model model) {
        model.addAttribute("driver", new Driver());
        return "scan-vehicle";
    }

    @GetMapping("/driver-profile")
    public String driverProfile(Model model, HttpServletRequest httpServletRequest) {
        Driver driver = driverService.findById((int) httpServletRequest.getSession().getAttribute("loggedDriverId"));

        model.addAttribute("driver", driver);
        return "driver-profile";
    }

    @PostMapping("/driver-register")
    public String save(@ModelAttribute Driver driver, Model model) {


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", "chazool");
        map.add("password", "chazool");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("mobile", "pin"));


        ResponseEntity<OAuth2AccessToken> accessTokenResponseEntity = restTemplate.exchange("http://authorization/oauth/token", HttpMethod.POST, request, OAuth2AccessToken.class);

        System.out.println(accessTokenResponseEntity.getBody().getValue());

//        Response response = driverService.save(driver);
//        model.addAttribute("response", response);
//        model.addAttribute("driver", new Driver());

        return "driver-register";
    }


    @PostMapping("/driver-activeVehicle")
    public String update(@ModelAttribute Driver driver, Model model, HttpServletRequest httpServletRequest) {
        String exceptionMessage = null;
        driver.setId((int) httpServletRequest.getSession().getAttribute("loggedDriverId"));
        try {
            driverService.setActiveVehicle(driver.getId(), driver.getActiveVehicle());
            return "index";
        } catch (VehicleNotFoundException vehicleNotFoundException) {
            exceptionMessage = vehicleNotFoundException.getMessage();
        }
        model.addAttribute("error", exceptionMessage);

        return "scan-vehicle";

    }

    @PostMapping("/driver-profile")
    public String updateDriverProfile(@ModelAttribute Driver driver, Model model, HttpServletRequest httpServletRequest) {
        int driverId = (int) httpServletRequest.getSession().getAttribute("loggedDriverId");
        Driver oldDriver = driverService.findById(driverId);

        driver.setId(driverId);
        driver.setPassword(oldDriver.getPassword());
        driver.setActive(true);
        driverService.update(driver);

        driver = driverService.findById(driverId);

        model.addAttribute("driver", driver);
        return "driver-profile";
    }

}
