package webflux.config;

import java.time.Duration;
import java.util.Random;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.pojo.User;

@Configuration
@EnableWebFlux
@ComponentScan("webflux")
public class WebFluxConfig {
  // Functional webflux endpoint configuration
  @Bean
  public RouterFunction<ServerResponse> routerFunction() {
    return RouterFunctions.route()
        .GET(
            "/functional",
            request ->
                ServerResponse.ok()
                    .body(Mono.just("Hello from a WebFlux functional endpoint!"), String.class))
        .GET(
            "random",
            request ->
                ServerResponse.ok()
                    .body(
                        Flux.interval(Duration.ofSeconds(1))
                            .map(i -> new Random().nextInt(1000) + ", "),
                        String.class))
        .GET(
            "/user",
            request -> ServerResponse.ok().body(Mono.just(new User(1, "John", 30)), User.class))
        .build();
  }
}
