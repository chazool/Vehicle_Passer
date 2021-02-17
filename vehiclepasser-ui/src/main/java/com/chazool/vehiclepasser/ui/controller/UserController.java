package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import com.chazool.highwayvehiclepasser.model.exception.EmailNotFoundException;
import com.chazool.highwayvehiclepasser.model.exception.InvalidEmailException;
import com.chazool.highwayvehiclepasser.model.exception.InvalidPasswordException;
import com.chazool.vehiclepasser.ui.model.User;
import com.chazool.vehiclepasser.ui.service.DriverService;
import com.chazool.vehiclepasser.ui.service.UserService;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
//@CrossOrigin(origins = "192.168.8.180:8181")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DriverService driverService;


   // @RequestMapping(value = "/login")
    public String login(User user, Model model, HttpSession httpSession) {
        String exceptionMessage = null;
        try {
            if (userService.login(user.getUsername(), user.getPassword())) {
                httpSession.setAttribute("loggedDriverId", driverService.findByEmail(user.getUsername()).getId());
                return "index";
            }
        } catch (InvalidEmailException invalidEmailException) {
            exceptionMessage = invalidEmailException.getMessage();

        } catch (InvalidPasswordException invalidPasswordException) {
            exceptionMessage = invalidPasswordException.getMessage();
        }
        model.addAttribute("error", exceptionMessage);


        return "login";
    }

  //  @RequestMapping(value = "/")
    public String loginPage(Model model, HttpServletRequest httpServletRequest) {
        httpServletRequest.removeAttribute("loggedDriverId");
        model.addAttribute("user", new User());

        return "login";

    }

}
