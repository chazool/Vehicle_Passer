package com.chazool.highwayvehiclepasser.model.transactionservice;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Terminal")
@Data
public class Terminal {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int locationId;
    /***
     *  entrance = 0
     *  exit = 1
     */
    private char terminalType;
    private boolean isActive;




}
