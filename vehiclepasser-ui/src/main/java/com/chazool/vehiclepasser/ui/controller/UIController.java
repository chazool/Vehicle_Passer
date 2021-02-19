package com.chazool.vehiclepasser.ui.controller;

import com.chazool.vehiclepasser.ui.config.AccessToken;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
public class UIController extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/driver-register").permitAll()
                .anyRequest()
                .authenticated();
    }

    @RequestMapping(value = "/index")
    public String home(Model model) {
        model.addAttribute("username", AccessToken.getUsername());
        return "index";
    }

    @RequestMapping(value = "/")
    public String start() {
        return "start";
    }


}
