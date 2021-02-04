package com.chazool.highwayvehiclepasser.transsaction.repository;

import com.chazool.highwayvehiclepasser.model.transactionservice.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Integer> {


    Route findByEntranceAndExist(int entrance, int exit);


}
