package com.chazool.vehiclepasser.authorizationserver.repository;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
    
}
