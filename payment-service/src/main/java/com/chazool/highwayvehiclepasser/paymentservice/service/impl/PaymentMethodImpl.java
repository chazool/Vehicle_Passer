package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.exception.InvalidIdException;
import com.chazool.highwayvehiclepasser.model.exception.PaymentMethodNotFoundException;
import com.chazool.highwayvehiclepasser.model.exception.PaymentNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.paymentservice.repository.PaymentMethodRepository;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodImpl implements PaymentMethodService {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        paymentMethod.setActive(true);
        paymentMethod.setIssueDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod update(PaymentMethod paymentMethod) throws InvalidIdException {
        if (paymentMethodRepository.findById(paymentMethod.getId()).isPresent())
            return paymentMethodRepository.save(paymentMethod);
        else
            throw new InvalidIdException("Invalid PaymentMethod Id");
    }


    @Override
    public PaymentMethod delete(int id) {
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findById(id);
        PaymentMethod paymentMethod;
        if (optionalPaymentMethod.isPresent()) {
            paymentMethod = optionalPaymentMethod.get();
            paymentMethod.setActive(false);
            paymentMethod.setCloseDate(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
            return paymentMethodRepository.save(paymentMethod);
        } else
            throw new InvalidIdException("Invalid PaymentMethod Id");


    }

    @Override
    public PaymentMethod findById(int id) throws PaymentNotFoundException {
        Optional<PaymentMethod> optionalPaymentMethod = paymentMethodRepository.findByIdAndIsActive(id, true);
        if (optionalPaymentMethod.isPresent())
            return optionalPaymentMethod.get();
        else
            throw new PaymentMethodNotFoundException("Invalid Card");
    }

    @Override
    public PaymentMethod findByDriver(int driverId, boolean isActive) {
        return paymentMethodRepository.findByDriverAndIsActive(driverId, isActive);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod updateBalance(int id, BigDecimal amount) throws PaymentMethodNotFoundException {

        PaymentMethod paymentMethod = findById(id);
        paymentMethod.setBalanceAmount(paymentMethod.getBalanceAmount().add(amount));
        return update(paymentMethod);
    }


}
