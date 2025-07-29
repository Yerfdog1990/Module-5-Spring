package springtxdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import springtxdemo.jdbc.JdbcDemoConfig;
import springtxdemo.jdbc.service.AccountService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(JdbcDemoConfig.class)
public class AccountServiceTest {

    @Autowired
    @Qualifier("declarative")
    private AccountService accountService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp(){
        System.out.printf("AccountService type: %s%n", accountService.getClass().getName());
        jdbcTemplate.execute("drop table if exists accounts");
        jdbcTemplate.execute("create table accounts (id integer primary key auto_increment, account_name varchar(255), balance double)");
        jdbcTemplate.update("insert into accounts (id, account_name, balance) values (?, ?, ?)", 1, "John", 100000.0);
        jdbcTemplate.update("insert into accounts (id, account_name, balance) values (?, ?, ?)", 2, "Mary", 100000.0);
    }
    @Test
    void testTransferMoneyMethod(){
        // Check that the balance has been correctly updated
        accountService.transferMoney(1, 2, 100.0);
        double account1Balance = jdbcTemplate.queryForObject("select balance from accounts where id = 1", Double.class);
        double account2Balance = jdbcTemplate.queryForObject("select balance from accounts where id = 2", Double.class);
        assertEquals(99900.0, account1Balance);
        assertEquals(100100.0, account2Balance);
    }
    @Test
    void testTransferMoneyFailed(){
        jdbcTemplate.update("insert into accounts (id, account_name, balance) values (?, ?, ?)", 3, "Johnathan", 500.0);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> accountService.transferMoney(3, 1, 1000.0));

        // Check balance has been correctly updated
        double account1Balance = jdbcTemplate.queryForObject("select balance from accounts where id = ?", Double.class, 1);
        assertEquals(100000.0, account1Balance);
    }
}
