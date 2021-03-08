package com.chazool.vehiclepasser.ui.service.impl;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.ReloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReloadServiceImpl implements ReloadService {


    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Response save(Reload reload) {

        ResponseEntity<Response> responseEntity = restTemplate.exchange(
                "http://payment/services/recharge"
                , HttpMethod.POST
                , AccessToken.getHttpEntity(reload)
                , Response.class
        );
        return responseEntity.getBody();
    }

    @Override
    public List findByBetweenDateTime() {

        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate endDate = LocalDate.now();

        ResponseEntity<List> responseEntity = restTemplate.exchange(
                "http://payment/services/recharge?date1=" + startDate.toString() + "&date2=" + endDate.toString()
                , HttpMethod.GET
                , AccessToken.getHttpEntity()
                , List.class
        );

        List<Map<String, String>> reloadHistory = responseEntity.getBody();

        long noOfDates = ChronoUnit.DAYS.between(startDate, endDate);
        List<LocalDate> dates = Stream
                .iterate(startDate, date -> date.plusDays(1))
                .limit(noOfDates + 1)
                .collect(Collectors.toList());

        List<Map<String, String>> reloadHistoryWithAllDays = new ArrayList();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

        dates.forEach(day -> {
                    AtomicBoolean available = new AtomicBoolean(false);
                    reloadHistory.forEach(history -> {
                        if (LocalDate.parse(history.get("datetime"), dateTimeFormatter).compareTo(day) == 0) {
                            available.set(true);
                            reloadHistoryWithAllDays.add(history);
                        }
                    });

                    if (available.get() == false) {
                        Map map = new HashMap();
                        map.put("datetime", day.toString());
                        map.put("simpleDate", day.format(DateTimeFormatter.ofPattern("MMM dd")).toString());
                        map.put("amount", 0.00);
                        reloadHistoryWithAllDays.add(map);
                    }
                }
        );

        return reloadHistoryWithAllDays;
    }
}
