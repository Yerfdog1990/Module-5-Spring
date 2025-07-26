package springtxdemo.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import springtxdemo.hibernate.Account;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    public static void main(String[] args){
        DataSource dataSource = createDataSource();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table account(id int primary key auto_increment, account_name varchar(255), balance double)");
//        jdbcTemplate.update("insert into account(account_name, balance) values(?, ?)", "John", 120000.00);
//        jdbcTemplate.query("select * from account", (rs, rowNum) -> new Account(rs.getString("account_name"), rs.getDouble("balance")));

//        RowMapper<JdbcAccount> rowMapper = (rs, rowNum) -> {
//            JdbcAccount jdbcAccount = new JdbcAccount();
//            jdbcAccount.setId(rs.getInt("id"));
//            jdbcAccount.setAccount_name(rs.getString("account_name"));
//            jdbcAccount.setBalance(rs.getDouble("balance"));
//            return jdbcAccount;
//        };

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).usingGeneratedKeyColumns("id");
        simpleJdbcInsert.withTableName("account");
        Map<String, Object> columnToSet = new HashMap<>();
        columnToSet.put("account_name", "John");
        columnToSet.put("balance", 120000.00);
        simpleJdbcInsert.executeAndReturnKey(columnToSet);

        BeanPropertyRowMapper<JdbcAccount> beanPropertyRowMapper = new BeanPropertyRowMapper<>(JdbcAccount.class);
        List<JdbcAccount> accountList = jdbcTemplate.query("select * from account", beanPropertyRowMapper);
        System.out.println(accountList);
    }
    private static DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
