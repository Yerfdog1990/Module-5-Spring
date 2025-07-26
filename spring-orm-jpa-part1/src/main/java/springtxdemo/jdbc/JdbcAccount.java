package springtxdemo.jdbc;

import lombok.Data;

@Data
public class JdbcAccount {
    private Integer id;
    private String account_name;
    private double balance;
}
