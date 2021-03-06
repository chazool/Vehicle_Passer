package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.exception.LowBalanceException;
import com.chazool.highwayvehiclepasser.model.exception.PaymentMethodNotFoundException;
import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.paymentservice.repository.ReloadRepository;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import com.chazool.highwayvehiclepasser.paymentservice.service.ReloadService;
import io.micrometer.core.instrument.step.StepCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

@Service
public class ReloadServiceImpl implements ReloadService {

    @Autowired
    private ReloadRepository reloadRepository;
    @Autowired
    private PaymentMethodService paymentMethodService;


    @Override
    public Reload save(Reload reload) throws PaymentMethodNotFoundException, LowBalanceException {

        if (reload.getReloadAmount().compareTo(new BigDecimal("99.00")) <= 0)
            throw new LowBalanceException("Your amount less than 0 please recharge greater than 100");

        reload.setDateTime(LocalDateTime.now(ZoneId.of("Asia/Colombo")).toString());
        reload.setActive(true);

        paymentMethodService.updateBalance(reload.getCard(), reload.getReloadAmount());
        return reloadRepository.save(reload);
    }

    @Override
    public List<Reload> findByCard(int card) {
        return reloadRepository.findByCard(card);
    }

    @Override
    public List<Map<String,String>> findByBetweenDateTime(String date1, String date2) {
        return reloadRepository.findByBetweenDateTime(date1, date2);
    }
}
