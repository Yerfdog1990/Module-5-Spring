package webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UndertowController {
  @GetMapping("/undertow")
  public Mono<String> hello() {
    return Mono.just("Hello from Undertow!");
  }
}
