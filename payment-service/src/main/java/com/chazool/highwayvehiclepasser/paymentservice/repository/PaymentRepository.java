package com.chazool.highwayvehiclepasser.paymentservice.repository;

import com.chazool.highwayvehiclepasser.model.paymentservice.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
