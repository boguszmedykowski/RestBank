package com.medykowski.RestBank.repository;

import com.medykowski.RestBank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<Account> getAll() {
        return jdbcTemplate.query("SELECT id, first_name, last_name, balance FROM Account",
                BeanPropertyRowMapper.newInstance(Account.class));
    }

    public Account getAccountById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, first_name, last_name, balance FROM Account WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Account.class), id);
    }

    public void createAccount(Account account) {
        jdbcTemplate.update("INSERT INTO Account (id, first_name, last_name, balance) VALUES (?, ?, ?, ?)",
                account.getId(), account.getFirstName(), account.getLastName(), account.getBalance());
    }
}
