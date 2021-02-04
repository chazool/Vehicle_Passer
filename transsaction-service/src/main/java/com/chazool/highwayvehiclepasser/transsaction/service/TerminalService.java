package com.chazool.highwayvehiclepasser.transsaction.service;


import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;

import java.util.List;

public interface TerminalService {


    Terminal save(Terminal terminal);

    Terminal update(Terminal terminal);

    Terminal delete(int id);

    Terminal findById(int id);

    List<Terminal> findByAll();

    List<Terminal> findByLocation(int locationId);


}
