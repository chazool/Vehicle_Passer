package com.chazool.vehiclepasser.ui.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;
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
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails) ?
                ((UserDetails) principal).getUsername() : principal.toString();
    }

    public static HttpEntity getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", getAccessToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }

    public static HttpEntity<?> getHttpEntity(Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", getAccessToken());
        HttpEntity httpEntity = new HttpEntity(body, httpHeaders);
        return httpEntity;
    }

}
