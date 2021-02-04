package com.chazool.highwayvehiclepasser.model.transactionservice;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicletype")
@Data
@ToString
public class VehicleType {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private BigDecimal charge;
    private boolean isActive;


}
