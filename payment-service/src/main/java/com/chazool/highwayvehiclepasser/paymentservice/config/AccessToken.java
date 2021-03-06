package com.chazool.highwayvehiclepasser.paymentservice.config;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class AccessToken {


    public static  String getAccessToken() {
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails =
                (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return oAuth2AuthenticationDetails.getTokenType().concat(" ").concat(oAuth2AuthenticationDetails.getTokenValue());
    }

    public static HttpEntity getHttpEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", getAccessToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return httpEntity;
    }

}
