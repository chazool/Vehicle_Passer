package com.chazool.vehiclepasser.ui.controller;

import com.chazool.highwayvehiclepasser.model.paymentservice.Reload;
import com.chazool.highwayvehiclepasser.model.responsehandle.Response;
import com.chazool.vehiclepasser.ui.config.AccessToken;
import com.chazool.vehiclepasser.ui.service.ReloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RestController
public class ReloadController {


    @Autowired
    private ReloadService reloadService;

    @RequestMapping(value = "recharge")
    public String recharge(Model model) {
        model.addAttribute("username", AccessToken.getUsername());
        model.addAttribute("reload", new Reload());
        return "recharge";
    }

    @PostMapping(value = "recharge")
    public String recharge(@ModelAttribute Reload reload, Model model) {
        model.addAttribute("username", AccessToken.getUsername());

        Response response = reloadService.save(reload);
        model.addAttribute("response", response);

        if (response.isAction())
            model.addAttribute("reload", new Reload());
        else
            model.addAttribute("reload", reload);

        return "recharge";
    }


    @GetMapping(value = "find-recharge-summary")
    public List findByBetweenDateTime() {

        return reloadService.findByBetweenDateTime();
    }

}
