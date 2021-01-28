package com.chazool.highwayvehiclepasser.model.transactionservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "routeFee")
public class RouteFee {

    @Id
    @GeneratedValue
    private int id;
    private int entrance;
    private int exist;
    private BigDecimal fee;
    private boolean isActive;




}
