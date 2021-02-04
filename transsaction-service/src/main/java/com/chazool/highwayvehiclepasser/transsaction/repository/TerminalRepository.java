package com.chazool.highwayvehiclepasser.transsaction.repository;

import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminalRepository extends JpaRepository<Terminal, Integer> {

    List<Terminal> findByLocationId(int locationId);
}
