package com.chazool.highwayvehiclepasser.model.transactionservice;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Terminal")
@Data
@ToString
public class Terminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
