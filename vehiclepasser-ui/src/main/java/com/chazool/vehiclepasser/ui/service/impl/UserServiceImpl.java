package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.authorizationserver.Role;
import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.InvalidEmailException;
import com.chazool.highwayvehiclepasser.model.exception.InvalidPasswordException;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    DriverService driverService;

    @Override
    public boolean login(String username, String password) throws InvalidEmailException, InvalidPasswordException {

        Driver driver = driverService.findByEmail(username);
        if (driver.getEmail() == null) {
            throw new InvalidEmailException("Invalid User Name");
        } else {
            if (driver.getPassword().equals(password)) {
                return true;
            } else {
                throw new InvalidPasswordException("Invalid Password");
            }
        }
    }

    @Override
    public Response save(User user) {
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId(2);
        roles.add(role);
        user.setRoles(roles);
        return restTemplate.postForObject("http://authorization/auth/users/signup", user, Response.class);
    }

    @Override
    public OAuth2AccessToken getAccessToken(String username, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "password");
        map.add("username", username);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("mobile", "pin"));

        ResponseEntity<OAuth2AccessToken> accessTokenResponseEntity = restTemplate.exchange("http://authorization/oauth/token", HttpMethod.POST, request, OAuth2AccessToken.class);

        return accessTokenResponseEntity.getBody();


    }
}
