package com.chazool.highwayvehiclepasser.transsaction.controller;

import com.chazool.highwayvehiclepasser.model.transactionservice.Terminal;
import com.chazool.highwayvehiclepasser.transsaction.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/terminal")
public class TerminalController {

    @Autowired
    private TerminalService terminalService;

    @PostMapping
    public Terminal save(@RequestBody Terminal terminal) {
        return terminalService.save(terminal);
    }

    @PutMapping
    public Terminal update(@RequestBody Terminal terminal) {
        return terminalService.update(terminal);
    }

    @DeleteMapping(value = "/{id}")
    public Terminal delete(@PathVariable int id) {
        return terminalService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public Terminal findById(@PathVariable int id) {
        return terminalService.findById(id);
    }

    @GetMapping
    public List<Terminal> findByAll() {
        return terminalService.findByAll();
    }

    @GetMapping(value = "/location/{locationId}")
    public List<Terminal> findByLocation(@PathVariable int locationId) {
        return terminalService.findByLocation(locationId);
    }
}
