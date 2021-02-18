package com.chazool.vehiclepasser.authorizationserver.service;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.vehiclepasser.authorizationserver.model.AuthUserDetail;
import com.chazool.vehiclepasser.authorizationserver.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private UserDetailRepository userDetailRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or Password Wrong"));
        UserDetails userDetails = new AuthUserDetail(optionalUser.get());

        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }

    @Override
    public User save(User user) {
        return userDetailRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {

        Optional<User> optionalUser = userDetailRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username wrong"));

        return optionalUser.get();
    }
}
