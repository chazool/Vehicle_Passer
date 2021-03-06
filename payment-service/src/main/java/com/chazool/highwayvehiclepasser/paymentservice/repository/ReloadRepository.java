package com.chazool.highwayvehiclepasser.paymentservice.repository;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ReloadRepository extends JpaRepository<Reload, Integer> {

    List<Reload> findByCard(int card);

    @Query(value = "SELECT DATE_FORMAT(datetime, \"%Y-%m-%d\") as datetime, DATE_FORMAT(datetime, \"%b %d\") as simpleDate,sum(reloadAmount)  as amount  " +
            " FROM Reload where dateTime between :date1 and :date2 group by date(dateTime)", nativeQuery = true)
    List<Map<String, String>> findByBetweenDateTime(@Param("date1") String date1, @Param("date2") String date2);


}
