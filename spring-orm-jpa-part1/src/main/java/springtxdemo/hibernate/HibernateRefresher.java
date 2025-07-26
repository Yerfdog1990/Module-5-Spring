package springtxdemo.hibernate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HibernateRefresher {
    public static void main(String[] args){
        Map<String, Object> hibernateSetting = new HashMap<>();
        hibernateSetting.put(AvailableSettings.JAKARTA_NON_JTA_DATASOURCE, createDataSource());
        hibernateSetting.put(AvailableSettings.DIALECT, "org.hibernate.dialect.H2Dialect");
        hibernateSetting.put(AvailableSettings.SHOW_SQL, true);
        hibernateSetting.put(AvailableSettings.HBM2DDL_AUTO, "update");
        hibernateSetting.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        hibernateSetting.put(AvailableSettings.STATEMENT_BATCH_SIZE, 10);
        hibernateSetting.put(AvailableSettings.USE_SQL_COMMENTS, true);
        hibernateSetting.put(AvailableSettings.FORMAT_SQL, true);
        hibernateSetting.put(AvailableSettings.AUTOCOMMIT, false);

        StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(hibernateSetting);

        StandardServiceRegistry serviceRegistry = registryBuilder.build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Account.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();

        Session querySession;
        try (SessionFactory sessionFactory = metadata.buildSessionFactory()){
            Session insertionSession = sessionFactory.openSession();
            Transaction transaction = insertionSession.beginTransaction();
            insertionSession.persist(new Account("John", 120000.00));
            transaction.commit();


           querySession = sessionFactory.openSession();
            Query<Account> query = querySession.createQuery("from Account", Account.class);
            List<Account> accounts = query.getResultList();
            System.out.println(accounts);
        }

    }

    private static DataSource createDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;");
        hikariConfig.setUsername("sa");
        hikariConfig.setPassword("");
        hikariConfig.setMaximumPoolSize(3);
        return new HikariDataSource(hikariConfig);
    }
}
