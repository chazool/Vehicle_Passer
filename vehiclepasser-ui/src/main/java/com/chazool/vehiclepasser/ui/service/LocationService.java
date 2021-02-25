package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.transactionservice.Location;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;

import java.util.List;

public interface LocationService {


    List<Location> findAllLocations();

    Location findLocationById(int id);

    List<Terminal> findTerminal(int location,int terminalType);

    Terminal findTerminalById(int id);


}
