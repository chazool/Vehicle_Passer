package com.chazool.highwayvehiclepasser.driverservice.repository;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByOwnerId(int ownerId);

}
