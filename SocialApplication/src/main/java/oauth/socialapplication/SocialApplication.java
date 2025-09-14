package oauth.socialapplication;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
public class SocialApplication {
    
    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Collections.emptyMap();
        }
        return Collections.singletonMap("name", principal.getAttribute("name"));
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/index.html",
                                "/webjars/**",
                                "/css/**",
                                "/js/**",
                                "/login**",
                                "/oauth2/**",
                                "/user",
                                "/facebook/data-deletion",
                                "/facebook/deletion-status"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        // Disable CSRF for logout endpoint
                        .ignoringRequestMatchers("/logout")
                )
                .logout(logout -> {
                    logout.logoutUrl("/logout")
                          .logoutSuccessUrl("/?logout")
                          .deleteCookies("JSESSIONID")
                          .invalidateHttpSession(true)
                          .clearAuthentication(true)
                          .addLogoutHandler(new SecurityContextLogoutHandler())
                          .permitAll();
                    // Configure logout to return 200 on success (no redirect)
                    logout.logoutSuccessHandler((request, response, authentication) -> {
                        response.setStatus(HttpServletResponse.SC_OK);
                    });
                })
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/?error")
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/oauth2/authorization")
                        )
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/login/oauth2/code/*")
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oauth2UserService())
                        )
                );
        // @formatter:on
        return http.build();
    }
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        final DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return request -> {
            try {
                OAuth2User user = delegate.loadUser(request);
                return user;
            } catch (Exception e) {
                throw new InternalAuthenticationServiceException("Failed to load user", e);
            }
        };
    }
    
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        final OidcUserService delegate = new OidcUserService();
        return request -> {
            try {
                OidcUser user = delegate.loadUser(request);
                return user;
            } catch (Exception e) {
                throw new InternalAuthenticationServiceException("Failed to load user", e);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SocialApplication.class, args);
    }

}
