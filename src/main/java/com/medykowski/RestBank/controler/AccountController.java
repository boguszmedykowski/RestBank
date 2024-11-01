package com.medykowski.RestBank.controler;

import com.medykowski.RestBank.model.Account;
import com.medykowski.RestBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/account")
    public List<Account> getAll() {
        return accountRepository.getAll();
    }
}
