package com.chazool.highwayvehiclepasser.model.driverservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver")
@Data
public class Driver {

    @Id
    @GeneratedValue
    private int id;
    private String fName;
    private String lName;
    private String dLicenseNo;
    private char gender;
    private String email;
    private int phoneNo;
    private LocalDateTime registrationDate;
    private boolean isActive;




}
