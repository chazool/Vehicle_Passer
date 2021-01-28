package com.chazool.highwayvehiclepasser.model.paymentservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
public class Payment {
    @Id
    @GeneratedValue
    private int id;
    private int vehicle;
    private int driver;
    private LocalDateTime entranceDate;
    private LocalDateTime exitDate;
    private int entranceGate;
    private int exitGate;
    private BigDecimal charge;
    private boolean isComplete;
    private boolean isSendEmail;






}
