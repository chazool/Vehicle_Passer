package com.chazool.vehiclepasser.authorizationserver.service;

import com.chazool.vehiclepasser.authorizationserver.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
