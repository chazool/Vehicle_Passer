package com.chazool.highwayvehiclepasser.paymentservice.repository;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReloadRepository extends JpaRepository<Reload, Integer> {

    List<Reload> findByCard(int card);


}
