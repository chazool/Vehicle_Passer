package com.chazool.highwayvehiclepasser.paymentservice.repository;

import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {

    Optional<PaymentMethod> findByIdAndIsActive(int id, boolean isActive);

    PaymentMethod findByDriverAndIsActive(int driver, boolean isActive);
}
