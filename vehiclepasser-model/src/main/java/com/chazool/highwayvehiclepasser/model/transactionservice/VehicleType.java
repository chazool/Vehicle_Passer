package com.chazool.highwayvehiclepasser.model.transactionservice;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vehicletype")
@Data
@ToString
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private BigDecimal charge;
    private boolean isActive;


}
