package com.chazool.highwayvehiclepasser.driverservice.repository;

import com.chazool.highwayvehiclepasser.model.driverservice.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
}
