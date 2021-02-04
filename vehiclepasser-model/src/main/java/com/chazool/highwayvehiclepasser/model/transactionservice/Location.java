package com.chazool.highwayvehiclepasser.model.transactionservice;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
@Data
@ToString
public class Location {

    @Id
    @GeneratedValue
    private int id;
    private String description;
    private boolean isActive;

}
