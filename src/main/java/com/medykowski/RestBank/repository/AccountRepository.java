package com.medykowski.RestBank.repository;

import com.medykowski.RestBank.model.Account;
import org.jetbrains.annotations.NotNull;
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
        return jdbcTemplate.query("SELECT id, firstName, lastName, balance FROM Account",
                BeanPropertyRowMapper.newInstance(Account.class));
    }

    public Account getAccountById(int id) {
        return jdbcTemplate.queryForObject("SELECT id, firstName, lastName, balance FROM Account WHERE id = ?",
                BeanPropertyRowMapper.newInstance(Account.class), id);
    }

    public void createAccount(@NotNull Account account) {
        jdbcTemplate.update("INSERT INTO Account (id, firstName, lastName, balance) VALUES (?, ?, ?, ?)",
                account.getId(), account.getFirstName(), account.getLastName(), account.getBalance());
    }


    public void updateAccount(int id, @org.jetbrains.annotations.NotNull Account account) {
        StringBuilder sql = new StringBuilder("UPDATE Account SET ");
        boolean first = true;

        if (account.getFirstName() != null) {
            sql.append("firstName = ?");
            first = false;
        }
        if (account.getLastName() != null) {
            if (!first) sql.append(", ");
            sql.append("lastName = ?");
            first = false;
        }
        if (account.getBalance() != 0) {
            if (!first) sql.append(", ");
            sql.append("balance = ?");
        }
        sql.append(" WHERE id = ?");

        jdbcTemplate.update(sql.toString(), ps -> {
            int index = 1;
            if (account.getFirstName() != null) {
                ps.setString(index++, account.getFirstName());
            }
            if (account.getLastName() != null) {
                ps.setString(index++, account.getLastName());
            }
            if (account.getBalance() != 0) {
                ps.setDouble(index++, account.getBalance());
            }
            ps.setInt(index, id);
        });
    }

    public void updateBallance(int id, double balance) {
        jdbcTemplate.update("UPDATE Account SET balance = ? WHERE id = ?", balance, id);
    }
}
