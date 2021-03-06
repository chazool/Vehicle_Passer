package com.chazool.vehiclepasser.ui.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;

import java.util.List;
import java.util.Map;

public interface ReloadService {
    Response save(Reload reload);

    List findByBetweenDateTime();

}
