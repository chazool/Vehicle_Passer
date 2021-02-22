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
@Table(name = "reload")
@Data
@ToString
public class Reload {

    @Id
    @GeneratedValue
    private int id;
    private int card;
    private BigDecimal reloadAmount;
    private LocalDateTime dateTime;
    private boolean isActive;

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime != null ? LocalDateTime.parse(dateTime) : null;
    }
}
