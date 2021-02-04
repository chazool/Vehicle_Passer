package com.chazool.highwayvehiclepasser.model.paymentservice;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Data
@ToString
public class Payment {
    @Id
    @GeneratedValue
    private int id;
    private int vehicle;
    private int driver;
    private LocalDateTime entranceDate;
    private LocalDateTime exitDate;
    private int entranceTerminal;
    private int exitTerminal;
    private BigDecimal charge;
    private boolean isComplete;
    private boolean isSendEmail;
    private int paymentMethod;


}
