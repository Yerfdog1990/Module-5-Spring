package wefluxdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.*;
import java.time.Duration;

@RestController
public class HelloController {
    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> hello() {
        return Mono.just("Hello World from Spring WebFlux!");
    }
    @GetMapping(value = "/consecutive-numbers", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> consecutiveNumbers(){
        return Flux.interval(Duration.ofMillis(500))
                .map(tick -> tick + ", ")
                .take(10);
    }
    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<User> getUser(){
        return Mono.just(new User(1, "John", 23));
    }
}
