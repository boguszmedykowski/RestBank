package com.medykowski.RestBank.controler;

import com.medykowski.RestBank.model.Account;
import com.medykowski.RestBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("")
    public List<Account> getAll() {
        return accountRepository.getAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable("id") int id) {
        return accountRepository.getAccountById(id);
    }

    @PostMapping("")
    public void createAccount(@RequestBody Account account) {
        accountRepository.createAccount(account);
    }

    @PatchMapping("/{id}")
    public void updateAccount(@PathVariable("id") int id, @RequestBody Account account) {
        accountRepository.updateAccount(id, account);
    }
}
