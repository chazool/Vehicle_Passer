package com.chazool.highwayvehiclepasser.emailservice.repository;

import com.chazool.highwayvehiclepasser.model.emailservice.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Integer> {
}
