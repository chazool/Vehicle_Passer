package com.chazool.highwayvehiclepasser.model.driverservice;

import com.chazool.highwayvehiclepasser.model.authorizationserver.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import sun.security.util.Password;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "driver")

@ToString

public class Driver {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;
    private String drivingLicenseNo;
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }


    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getActiveVehicle() {
        return activeVehicle;
    }

    public void setActiveVehicle(int activeVehicle) {
        this.activeVehicle = activeVehicle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
