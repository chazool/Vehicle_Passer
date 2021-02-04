package com.chazool.highwayvehiclepasser.model.driverservice;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "vehicle")
@Data
@ToString
public class Vehicle {

    @Id
    @GeneratedValue
    private int id;
    private String vehicleNo;
    private int ownerId;
    private int vehicleType;
    /***
     * System Registration Date Time
     */
    private LocalDateTime registrationDate;
    private LocalDateTime deleteDate;
    private boolean isActive;


}
