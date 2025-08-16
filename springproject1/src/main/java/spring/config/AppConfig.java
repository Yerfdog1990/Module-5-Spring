package spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.cfg.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    private HikariDataSource hikariDataSource;
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(getDataSource());
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setPackagesToScan("spring.entity");
        return sessionFactoryBean;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        return properties;
    }

    @Bean
    protected DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("com.p6spy.engine.spy.P6SpyDriver");
        dataSource.setJdbcUrl("jdbc:p6spy:mysql://localhost:3306/todo");
        dataSource.setMaximumPoolSize(10);
        dataSource.setUsername("yerfdog");
        dataSource.setPassword("Cyril@2019");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory factory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(factory);
        return transactionManager;
    }

    /**
     * Ensures proper cleanup of resources at application shutdown.
     */
    /*
      MENTOR
      The cleanup code in the `@PreDestroy` method is not strictly necessary,
      but it's a good practice. HikariCP is well-behaved and will automatically close its
      connection pool when the JVM shuts down. However, explicitly closing it ensures resources
      are released cleanly and immediately.
      Also, modern JDBC drivers typically handle their own cleanup. This is mostly a legacy
      concern from older JDBC drivers that might leak resources.
      Bottom line: we can safely remove this cleanup code if we're using recent versions of:
      - HikariCP (2.3.8+)
      - MySQL Connector/J (8.0+)
      - A modern JVM

      However, keeping it doesn't hurt and makes the cleanup explicit.
     */
    @PreDestroy
    public void cleanup() {
        // Close the Hikari DataSource
        if (hikariDataSource != null && !hikariDataSource.isClosed()) {
            hikariDataSource.close();
        }

        // Deregister the JDBC driver to avoid memory leaks
        try {
            DriverManager.getDrivers()
                    .asIterator()
                    .forEachRemaining(
                            driver -> {
                                try {
                                    DriverManager.deregisterDriver(driver);
                                } catch (SQLException e) {
                                    System.err.println("Error deregistering JDBC driver: " + e.getMessage());
                                }
                            });
        } catch (Exception e) {
            System.err.println("Error during JDBC driver cleanup: " + e.getMessage());
        }

        // Clean up MySQL AbandonedConnectionCleanupThread
        com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
