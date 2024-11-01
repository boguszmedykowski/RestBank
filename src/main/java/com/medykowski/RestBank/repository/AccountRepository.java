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
}
