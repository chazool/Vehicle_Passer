package com.chazool.highwayvehiclepasser.transsaction.repository;

import com.chazool.highwayvehiclepasser.model.transactionservice.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {
}
