package com.medykowski.RestBank.controler;

import com.medykowski.RestBank.dto.AccountDTO;
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
    public void createAccount(@RequestBody AccountDTO accountDTO) {
        Account account = new Account();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setBalance(accountDTO.getBalance());
        accountRepository.createAccount(account);
    }

    @PatchMapping("/{id}")
    public void updateAccount(@PathVariable("id") int id, @RequestBody Account account) {
        accountRepository.updateAccount(id, account);
    }
}