package springtxdemo.hibernate.account;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("springtxdemo.hibernate")
@EnableTransactionManagement
public class HibernateAppConfig {

    @Bean
    @Qualifier("hibernateDataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("org.h2.Driver");
        managerDataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        managerDataSource.setUsername("sa");
        managerDataSource.setPassword("");
        return managerDataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory(@Qualifier("hibernateDataSource") DataSource dataSource){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setPackagesToScan("springtxdemo.hibernate.entity");

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.H2Dialect");
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");
        localSessionFactoryBean.setHibernateProperties(properties);
        return localSessionFactoryBean;
    }
    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory){
        return new HibernateTransactionManager(sessionFactory);
    }
}
