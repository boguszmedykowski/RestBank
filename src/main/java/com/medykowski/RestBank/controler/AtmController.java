package com.medykowski.RestBank.controler;

import com.medykowski.RestBank.service.Atm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atm")
public class AtmController {

    @Autowired
    Atm atmService;

    @GetMapping("/balance/{id}")
    public double getBalance(@PathVariable("id") int id) {
        return atmService.getBalance(id);
    }

    @PostMapping("/withdraw/{id}")
    public double withdraw(@RequestBody double amount, @PathVariable("id") int id) {
        return atmService.withdraw(amount, id);
    }
}