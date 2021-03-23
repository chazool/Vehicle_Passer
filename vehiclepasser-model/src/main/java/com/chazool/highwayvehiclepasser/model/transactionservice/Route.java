package com.chazool.highwayvehiclepasser.model.transactionservice;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "route")
@Data
@ToString
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int entrance;
    private int exist;
    private BigDecimal fee;
    private boolean isActive;




}
