package com.chazool.highwayvehiclepasser.model.transactionservice;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gate")
@Data
public class Gate {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int locationId;
    /***
     *  entrance = 0
     *  exit = 1
     */
    private char gateType;
    private boolean isActive;




}
