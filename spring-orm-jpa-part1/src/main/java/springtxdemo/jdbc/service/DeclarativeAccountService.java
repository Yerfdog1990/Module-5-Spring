package springtxdemo.jdbc.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


public class DeclarativeAccountService implements AccountService {
    private final JdbcTemplate jdbcTemplate;

    public DeclarativeAccountService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }
    @Override
    @Transactional
    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        Double fromAccountBalance =
                jdbcTemplate.queryForObject(
                        "select balance from accounts where id = ?", Double.class, fromAccountId);
        if (fromAccountBalance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        // Subtract from the source account
        jdbcTemplate.update(
                "update accounts set balance = balance - ? where id = ?", amount, fromAccountId);
        // Add to the destination account
        jdbcTemplate.update(
                "update accounts set balance = balance + ? where id = ?", amount, toAccountId);
    }
}
