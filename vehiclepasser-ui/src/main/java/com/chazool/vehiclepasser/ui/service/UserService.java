package com.chazool.vehiclepasser.ui.service;


import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public interface UserService {

    boolean login(String username, String password);

    Response save(User user);

    OAuth2AccessToken getAccessToken(String username, String password);


}
