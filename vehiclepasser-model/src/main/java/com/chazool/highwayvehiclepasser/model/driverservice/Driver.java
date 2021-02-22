package com.chazool.highwayvehiclepasser.model.driverservice;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import lombok.Data;
import lombok.ToString;
import sun.security.util.Password;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver")
@Data
@ToString
public class Driver {

    @Id
    @GeneratedValue
    private int id;
    private String fName;
    private String lName;
    private String dLicenseNo;
    private char gender;
    @Column(unique = true)
    private String email;
    @Transient
    private String password;
    private String phoneNo;
    private LocalDateTime registrationDate;
    private boolean isActive;
    private int activeVehicle;
    private String username;

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate != null ? LocalDateTime.parse(registrationDate) : null;
    }
}
