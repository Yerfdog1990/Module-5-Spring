package jdbc.batchinsert;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
@Configuration
@ComponentScan("jdbc.batchinsert")
@EnableTransactionManagement
public class JdbcConfigDemo {
    @Bean
    @Qualifier("jdbcDataSource")
    public DataSource creetaDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    @Bean
    public PersonBatchRepositoryImpl personBatchRepository(JdbcTemplate jdbcTemplate){
        return new PersonBatchRepositoryImpl(jdbcTemplate);
    }
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("jdbcDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
