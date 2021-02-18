package com.chazool.vehiclepasser.authorizationserver.Controller;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.highwayvehiclepasser.model.exception.DuplicateEntryException;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.authorizationserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth/users")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/signup")
    public Response save(@RequestBody User user) {
        try {
            return Response.success(userService.save(user));
        } catch (DuplicateEntryException duplicateEntryException) {
            return Response.fail(duplicateEntryException.getMessage());
        }
    }

    @GetMapping(value = "/{username}")
    public User fetchByUserName(@PathVariable String username) {
        return userService.findByUsername(username);
    }


}
