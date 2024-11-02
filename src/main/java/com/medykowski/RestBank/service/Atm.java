package com.medykowski.RestBank.service;

import com.medykowski.RestBank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Atm {

    @Autowired
    AccountRepository accountRepository;

    public double withdraw(double amount, int id) {
        double balance = accountRepository.getAccountById(id).getBalance();
        if (balance < amount) {
            return -1;
        }
        accountRepository.updateBallance(id, balance - amount);
        return balance - amount;
    }

    public double getBalance(int id) {
        return accountRepository.getAccountById(id).getBalance();
    }
}