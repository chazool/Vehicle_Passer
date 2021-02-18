package com.chazool.vehiclepasser.authorizationserver.service;

import com.chazool.highwayvehiclepasser.model.authorizationserver.Permission;
import com.chazool.highwayvehiclepasser.model.authorizationserver.Role;
import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import com.chazool.highwayvehiclepasser.model.exception.DuplicateEntryException;
import com.chazool.vehiclepasser.authorizationserver.model.AuthUserDetail;
import com.chazool.vehiclepasser.authorizationserver.repository.UserDetailRepository;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService, UserService {


    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> optionalUser = userDetailRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or Password Wrong"));
        UserDetails userDetails = new AuthUserDetail(optionalUser.get());


        new AccountStatusUserDetailsChecker().check(userDetails);

        return userDetails;
    }

    @Override
    public User save(User user) throws DuplicateEntryException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            user = userDetailRepository.save(user);
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
           throw new DuplicateEntryException("Username already exit. please try with another name");
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {

        Optional<User> optionalUser = userDetailRepository.findByUsername(username);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username wrong"));

        return optionalUser.get();
    }
}
