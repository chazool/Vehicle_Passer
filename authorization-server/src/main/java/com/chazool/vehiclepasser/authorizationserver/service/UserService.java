package com.chazool.vehiclepasser.authorizationserver.service;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;

public interface UserService {
    User save(User user);

    User findByUsername(String username);
}
