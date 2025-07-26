package springtxdemo.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import springtxdemo.jdbc.dao.AccountRegistry;
import springtxdemo.jdbc.dao.JdbcTemplateAccountRegistry;
import springtxdemo.jdbc.service.AccountService;
import springtxdemo.jdbc.service.ProgrammaticAccountService;

import javax.sql.DataSource;

@Configuration
public class JdbcDemoConfig {

    @Bean
    public DataSource createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public AccountRegistry accountRegistry(DataSource dataSource){
        return new JdbcTemplateAccountRegistry(dataSource);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new JdbcTransactionManager(dataSource);
    }
    @Bean
    public AccountService pragmaticAccountService(JdbcTemplate jdbcTemplate, PlatformTransactionManager transactionManager){
        return new ProgrammaticAccountService(jdbcTemplate, transactionManager);
    }
}
