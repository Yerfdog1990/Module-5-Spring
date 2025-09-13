package com.codegym.jira.config;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class TestDatabaseConfig {
    @Resource
    private DataSourceProperties properties;

    @Bean
    @Profile("test")
    public DataSource h2DataSource(){
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:jira-test;MODE=PostgreSQL;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false")
                .username("sa")
                .password("")
                .build();
    }
}
