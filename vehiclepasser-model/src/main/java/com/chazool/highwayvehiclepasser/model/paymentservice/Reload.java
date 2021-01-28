package com.chazool.highwayvehiclepasser.model.paymentservice;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reload")
@Data
public class Reload {

    @Id
    @GeneratedValue
    private int id;
    private int card;
    private int reloadAmount;
    private LocalDateTime dateTime;
    private boolean isActive;





}
