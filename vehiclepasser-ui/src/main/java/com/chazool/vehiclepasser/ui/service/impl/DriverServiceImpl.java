package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.authorizationserver.Role;
import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import com.chazool.highwayvehiclepasser.model.exception.VehicleNotFoundException;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.UserService;
import com.chazool.vehiclepasser.ui.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private UserService userService;

    @Override
    public Response save(Driver driver) {

        User user = new User();
        user.setUsername(driver.getUsername());
        user.setPassword(driver.getPassword());
        user.setEmail(driver.getEmail());
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);

        Response response = userService.save(user);
        OAuth2AccessToken oAuth2AccessToken = userService.getAccessToken(user.getUsername(), user.getPassword());

        if (response.isAction()) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization"
                    , oAuth2AccessToken.getTokenType().concat(" ").concat(oAuth2AccessToken.getValue()));
            HttpEntity<Driver> httpEntity = new HttpEntity<>(driver, httpHeaders);

            ResponseEntity<Response> responseEntity = restTemplate
                    .exchange("http://driver/services/drivers/register", HttpMethod.POST, httpEntity, Response.class);
            return responseEntity.getBody();
        } else {
            return response;
        }
    }

    @Override
    public Driver update(Driver driver) {
        restTemplate.put("http://driver/services/drivers/", driver);
        return findById(driver.getId());
    }

    @Override
    public Driver setActiveVehicle(int driverId, int activeVehicleId) throws VehicleNotFoundException {
        Vehicle vehicle = vehicleService.findById(activeVehicleId);

        Driver driver = findById(driverId);

        driver.setActiveVehicle(vehicle.getId());

        restTemplate.put("http://driver/services/drivers/", driver, Driver.class);

        return driver;
    }

    @Override
    public Driver findById(int id) {
        return restTemplate.getForObject("http://driver/services/drivers/" + id, Driver.class);
    }

    @Override
    public Driver findByEmail(String email) {

        Driver driver = restTemplate.getForObject("http://driver/services/drivers/email/" + email, Driver.class);
        return driver;
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }


}
