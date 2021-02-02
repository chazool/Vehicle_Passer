package com.chazool.highwayvehiclepasser.model.driverservice;

import lombok.Data;
import sun.security.util.Password;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver")
@Data
public class Driver {

    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = "Invalid Name ")
    private String fName;
    private String lName;
    private String dLicenseNo;
    private char gender;
    private String email;
    private String password;
    private int phoneNo;
    private LocalDateTime registrationDate;
    private boolean isActive;




}
