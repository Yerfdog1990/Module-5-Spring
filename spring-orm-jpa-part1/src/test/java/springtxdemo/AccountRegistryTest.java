package springtxdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import springtxdemo.jdbc.JdbcDemoConfig;
import springtxdemo.jdbc.dao.AccountRegistry;
import springtxdemo.jdbc.service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(JdbcDemoConfig.class)
public class AccountRegistryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AccountRegistry accountRegistry;
    @Qualifier("accountRegistry")
    @Autowired
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        // Ensure a consistent table name throughout
        jdbcTemplate.execute("drop table if exists accounts");
        jdbcTemplate.execute("create table accounts (id integer primary key auto_increment, name varchar(255), balance double)");
    }

    @Test
    void testRegisterAccountAndGetBalanceMethod() {
        // Test registering an account and fetching the balance
        int accountId = accountRegistry.registerAccount("John", 100.0);
        double accountBalance = accountRegistry.getBalance(accountId);
        assertEquals(100.0, accountBalance);
    }
}