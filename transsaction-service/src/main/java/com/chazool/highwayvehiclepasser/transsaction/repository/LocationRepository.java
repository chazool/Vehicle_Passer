package com.chazool.highwayvehiclepasser.transsaction.repository;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Integer> {
}
