package com.chazool.highwayvehiclepasser.paymentservice.repository;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import io.micrometer.core.instrument.step.StepCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByDriverAndIsComplete(int driver, boolean isComplete);

    @Query(value = "SELECT count(*) as vehicleCont," +
            "DATE_FORMAT(entranceDate, \"%Y-%m-%d\") as fullDate ," +
            "DATE_FORMAT(entranceDate, \"%b %d\") as simpleDate\n" +
            "FROM payment \n" +
            "where entranceTerminal in (:terminals) and date(entranceDate) between :startDate and DATE_ADD(:endDate, INTERVAL 1 DAY) \n" +
            "group by date(entranceDate) ", nativeQuery = true)
    List<Map> findVehicleCountByEntranceTerminalAndDate(
            @Param("terminals") List<Integer> terminals,
            @Param("startDate") String date1, @Param("endDate") String date2);

    @Query(value = "SELECT count(*) as vehicleCont," +
            "DATE_FORMAT(exitDate, \"%Y-%m-%d\") as fullDate ," +
            "DATE_FORMAT(exitDate, \"%b %d\") as simpleDate\n" +
            "FROM payment \n" +
            "where exitTerminal in (:terminals) and date(exitDate) between :startDate and DATE_ADD(:endDate, INTERVAL 1 DAY)  \n" +
            "group by date(exitDate) ", nativeQuery = true)
    List<Map> findVehicleCountByExitTerminalAndDate(
            @Param("terminals") List<Integer> terminals,
            @Param("startDate") String date1, @Param("endDate") String date2);


    @Query(value = "SELECT * FROM payment " +
            "where entranceTerminal in (:terminals)  and date(entranceDate) between :startDate and  DATE_ADD(:endDate, INTERVAL 1 DAY)  "
            , nativeQuery = true)
    List<Payment> findPaymentsByEntranceTerminalInAndDate(@Param("terminals") List<Integer> terminals
            , @Param("startDate") String startDate, @Param("endDate") String endDate);

    @Query(value = "SELECT * FROM payment " +
            "where exitTerminal in (:terminals)  and date(exitDate) between :startDate and  DATE_ADD(:endDate, INTERVAL 1 DAY)  "
            , nativeQuery = true)
    List<Payment> findPaymentsByExitTerminalInAndDate(@Param("terminals") List<Integer> terminals
            , @Param("startDate") String startDate, @Param("endDate") String endDate);


}
