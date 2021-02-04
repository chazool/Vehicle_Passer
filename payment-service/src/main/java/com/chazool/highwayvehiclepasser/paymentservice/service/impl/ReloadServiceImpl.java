package com.chazool.highwayvehiclepasser.paymentservice.service.impl;

import com.chazool.highwayvehiclepasser.model.paymentservice.PaymentMethod;
import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.paymentservice.repository.ReloadRepository;
import com.chazool.highwayvehiclepasser.paymentservice.service.PaymentMethodService;
import com.chazool.highwayvehiclepasser.paymentservice.service.ReloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReloadServiceImpl implements ReloadService {

    @Autowired
    private ReloadRepository reloadRepository;
    @Autowired
    private PaymentMethodService paymentMethodService;


    @Override
    public Reload save(Reload reload) {
        paymentMethodService.updateBalance(reload.getCard(), reload.getReloadAmount());
        return reloadRepository.save(reload);
    }

    @Override
    public List<Reload> findByCard(int card) {
        return reloadRepository.findByCard(card);
    }
}
