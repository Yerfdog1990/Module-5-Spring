package jdbc.rowmapper;

import jdbc.batchinsert.PersonBatchRepositoryImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan("jdbc.rowmapper")
@EnableTransactionManagement
public class RowMapperConfig {

    @Bean
    @Qualifier("mapperDataSource")
    public DataSource createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(@Qualifier("mapperDataSource") DataSource dataSource){
        return new NamedParameterJdbcTemplate(dataSource);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("mapperDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public PersonMapperRepositoryImpl personMapperRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        return new PersonMapperRepositoryImpl(jdbcTemplate, namedParameterJdbcTemplate);
    }
}
