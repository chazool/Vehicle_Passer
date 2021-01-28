package com.chazool.highwayvehiclepasser.model.emailservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email")
@Data
public class Email {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private int driverId;
    private boolean isActive;

}
