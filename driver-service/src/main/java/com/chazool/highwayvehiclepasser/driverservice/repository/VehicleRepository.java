package com.chazool.highwayvehiclepasser.driverservice.repository;

import com.chazool.highwayvehiclepasser.model.driverservice.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    List<Vehicle> findByOwnerId(int ownerId);

    Optional<Vehicle> findByOwnerIdAndVehicleNo(int ownerId, String vehicleNo);

}
