package com.chazool.highwayvehiclepasser.model.transactionservice;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "location")
@Data
@ToString
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean isActive;

}
