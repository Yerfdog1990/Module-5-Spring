package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails yerfdog = User
//                .withUsername("Yerfdog")
//                .password("$2a$10$N8fih8JMaTlXjieyl2oDKuJQJ7fVaJhPLbcEwd/WBbPPV3mG7jVuK")
//                .roles("ADMIN", "USER")
//                .build();
//
//        UserDetails cyrek = User
//                .withUsername("Cyrek")
//                .password("$2a$10$JaIwmZ29Q2bcGnUmk/47oOTJDDVx4Dz0hTHZufXAJAGb36rY1yog.")
//                .roles("USER")
//                .build();
//
//        UserDetails cyril = User
//                .withUsername("Cyril")
//                .password("$2a$10$LMS0etmFClkMBadnc0BlUu4EXkfVgXUim1U45kjvihXSs94.S6GVy")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(yerfdog, cyrek, cyril);
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> {
            customizer
                    .requestMatchers("/", "/hi", "/hello", "/register", "/registered-user", "/resources/**", "/WEB-INF/views/**").permitAll()
                    .anyRequest().authenticated();
        });
        http.csrf(AbstractHttpConfigurer::disable);
        http.formLogin(Customizer.withDefaults());
        http.logout(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUsername("myuser");
        driverManagerDataSource.setPassword("mypassword");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/security-db");
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        return driverManagerDataSource;
    }
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
