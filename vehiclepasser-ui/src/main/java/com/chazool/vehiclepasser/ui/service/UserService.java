package com.chazool.vehiclepasser.ui.service;


import com.chazool.highwayvehiclepasser.model.authorizationserver.User;

public interface UserService {

    boolean login(String username, String password);

    User save(User user);


}
