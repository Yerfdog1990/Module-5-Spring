package springsecurity.architecture.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/exists/{username}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/change-password").hasRole("USER")
                        .requestMatchers("/me").hasRole("USER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        // Use DelegatingPasswordEncoder to support "{bcrypt}" and future upgrades
       return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    // For database user management
    public UserDetailsManager userDetailsManager(PasswordEncoder encoder, ObjectProvider<DataSource> dataSourceProvider,@Lazy AuthenticationManager authenticationManager){
        DataSource dataSourceObj = dataSourceProvider.getIfAvailable();
        if(dataSourceObj != null){
            JdbcUserDetailsManager jdbcUserManager = new JdbcUserDetailsManager(dataSourceObj);
            jdbcUserManager.setAuthenticationManager(authenticationManager); // enables re-auth during changePassword
            if(!jdbcUserManager.userExists("john")){
                jdbcUserManager.createUser(
                        User.withUsername("john")
                                .password(encoder.encode("password"))
                                .roles("USER")
                                .build()
                );
            }
            if(!jdbcUserManager.userExists("admin")){
                jdbcUserManager.createUser(
                        User.withUsername("admin")
                                .password(encoder.encode("admin123"))
                                .roles("ADMIN")
                                .build()
                );
            }
            return jdbcUserManager;
        }
        // Fallback to in-memory when no DataSource is configured/available
        UserDetails john = User.withUsername("john")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();
        InMemoryUserDetailsManager inMemoryUserManager = new InMemoryUserDetailsManager(john, admin);
        inMemoryUserManager.setAuthenticationManager(authenticationManager);
        return inMemoryUserManager;
    }
    // Note:
    // Spring Boot autoconfigures providers for you when you expose a UserDetailsService bean and PasswordEncoder.
    // You can still register providers manually for customization.
}
