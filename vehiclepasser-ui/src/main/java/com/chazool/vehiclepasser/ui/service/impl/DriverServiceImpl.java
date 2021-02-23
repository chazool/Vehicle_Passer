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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.security.oauth2.client.AccessTokenContextRelay;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Access;
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

        if (response.isAction()) {
            OAuth2AccessToken oAuth2AccessToken = userService.getAccessToken(user.getUsername(), user.getPassword());
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
    public Response update(Driver driver) {
        Driver oldDriver = findByUsername(AccessToken.getUsername());

        driver.setId(oldDriver.getId());
        driver.setActive(true);
        driver.setRegistrationDate(oldDriver.getRegistrationDate().toString());
        driver.setUsername(oldDriver.getUsername());

        ResponseEntity<Response> responseEntity = restTemplate.exchange(
                "http://driver/services/drivers/"
                , HttpMethod.PUT
                , AccessToken.getHttpEntity(new JSONObject(driver))
                , Response.class);

        return responseEntity.getBody();
    }

    @Override
    public Response setActiveVehicle(int activeVehicleId) {

        Response vehicleResponse = vehicleService.findById(activeVehicleId);

        if (vehicleResponse.isAction()) {
            Driver driver = findByUsername(AccessToken.getUsername());
            driver.setActiveVehicle(activeVehicleId);

            ResponseEntity<Response> responseEntity = restTemplate.exchange(
                    "http://driver/services/drivers/"
                    , HttpMethod.PUT
                    , AccessToken.getHttpEntity(new JSONObject(driver))
                    , Response.class);

            return responseEntity.getBody();
        } else {
            return vehicleResponse;
        }
    }

    @Override
    public synchronized Driver findById(int id) {

        ResponseEntity<Driver> responseEntity = restTemplate.exchange(
                "http://driver/services/drivers/" + id
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , Driver.class);

        return responseEntity.getBody();
    }

    @Override
    public Driver findByEmail(String email) {
        Driver driver = restTemplate.getForObject("http://driver/services/drivers/email/" + email, Driver.class);
        return driver;
    }

    @Override
    public Driver findByUsername(String username) {
        ResponseEntity<Driver> responseEntity = restTemplate.exchange(
                "http://driver/services/drivers/username/" + username
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , Driver.class);
        return responseEntity.getBody();
    }

    @Override
    public List<Driver> findAll() {
        return null;
    }

}
