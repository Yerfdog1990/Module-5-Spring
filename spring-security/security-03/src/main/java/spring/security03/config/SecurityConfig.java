package spring.security03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails yerfdog = User
                .withUsername("Yerfdog")
                .password("$2a$10$N8fih8JMaTlXjieyl2oDKuJQJ7fVaJhPLbcEwd/WBbPPV3mG7jVuK")
                .roles("ADMIN", "USER")
                .build();

        UserDetails cyrek = User
                .withUsername("Cyrek")
                .password("$2a$10$JaIwmZ29Q2bcGnUmk/47oOTJDDVx4Dz0hTHZufXAJAGb36rY1yog.")
                .roles("USER")
                .build();

        UserDetails cyril = User
                .withUsername("Cyril")
                .password("$2a$10$LMS0etmFClkMBadnc0BlUu4EXkfVgXUim1U45kjvihXSs94.S6GVy")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(yerfdog, cyrek, cyril);
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
