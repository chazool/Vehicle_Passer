package com.chazool.highwayvehiclepasser.model.transactionservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @Id
    @GeneratedValue
    private int id;
    private String vehicleNo;
    private int ownerId;
    /***
     * System Registration Date Time
     */
    private LocalDateTime registrationDate;



}
