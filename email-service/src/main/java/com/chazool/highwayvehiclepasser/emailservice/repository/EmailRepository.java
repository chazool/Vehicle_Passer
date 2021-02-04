package com.chazool.highwayvehiclepasser.emailservice.repository;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Integer> {

    Optional<Email> findByDriverIdAndIsActive(int driverId, boolean isActive);


}
