package com.chazool.vehiclepasser.ui.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AccessToken {

    public static String getAccessToken() {
        OAuth2AuthenticationDetails authenticationDetail =
                (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        
        return authenticationDetail.getTokenType().concat(" ").concat(authenticationDetail.getTokenValue());
    }


    public static String getUsername() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getDetails();
        //  UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //   return userDetails.getUsername();
        return null;
    }

}
