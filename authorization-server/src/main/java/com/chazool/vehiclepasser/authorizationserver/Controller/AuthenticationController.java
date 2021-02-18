package com.chazool.vehiclepasser.authorizationserver.Controller;

import com.chazool.vehiclepasser.authorizationserver.model.User;
import com.chazool.vehiclepasser.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/users")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/signup")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping(value = "/{username}")
    public User fetchByUserName(@PathVariable String username) {
        return userService.findByUsername(username);
    }


}
