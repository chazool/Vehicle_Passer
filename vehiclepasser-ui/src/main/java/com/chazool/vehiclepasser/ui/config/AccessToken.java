package com.chazool.vehiclepasser.ui.config;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Collection;

public class AccessToken {

    public static synchronized String getAccessToken() {
        OAuth2AuthenticationDetails authenticationDetail =
                (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();


        return authenticationDetail.getTokenType().concat(" ").concat(authenticationDetail.getTokenValue());
    }

    public static String getUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (principal instanceof UserDetails) ?
                ((UserDetails) principal).getUsername() : principal.toString();
    }

    public static void printAuth() {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getPrincipal());
        System.out.println(auth.getCredentials().toString());
        System.out.println(auth.getAuthorities().size());
        System.out.println(auth.getDetails());


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

    public static HttpEntity<?> getHttpEntity(JSONObject body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", getAccessToken());
        HttpEntity httpEntity = new HttpEntity(body.toString(), httpHeaders);
        return httpEntity;
    }

}
