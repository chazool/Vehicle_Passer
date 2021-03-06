package com.chazool.highwayvehiclepasser.paymentservice.service;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.paymentservice.repository.ReloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface ReloadService {

    Reload save(Reload reload);

    List<Reload> findByCard(int card);

    List<Map<String, String>> findByBetweenDateTime(String date1, String date2);


}
