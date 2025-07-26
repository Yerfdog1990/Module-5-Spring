package springtxdemo.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class JdbcTemplateAccountRegistry implements AccountRegistry {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public JdbcTemplateAccountRegistry(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        // Use consistent table name 'accounts'
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("accounts") // Match table name created in the test setup
                .usingGeneratedKeyColumns("id") // Specify the auto-generated ID column
                .usingColumns("name", "balance"); // Explicitly configure columns to avoid empty VALUES()
    }

    @Override
    public int registerAccount(String accountName, double balance) {
        // Map the columns to their corresponding values
        Map<String, Object> columnToSet = new HashMap<>();
        columnToSet.put("name", accountName); // Match column name 'name' as per the 'create' statement in the test
        columnToSet.put("balance", balance);

        // Execute insert and return the generated key
        return simpleJdbcInsert.executeAndReturnKey(columnToSet).intValue();
    }

    @Override
    public double getBalance(int id) {
        // Fetch balance from the 'accounts' table for the given ID
        return jdbcTemplate.query("select balance from accounts where id = ?",
                (rs, rowNum) -> rs.getDouble("balance"), id).get(0);
    }
}