package com.chazool.highwayvehiclepasser.transsaction.repository;

import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalRepository extends JpaRepository<Terminal,Integer> {
}
