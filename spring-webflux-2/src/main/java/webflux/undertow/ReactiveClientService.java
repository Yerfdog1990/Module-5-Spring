package webflux.undertow;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.pojo.User;

public class ReactiveClientService {
  private final WebClient webClient;

  public ReactiveClientService(String baseUrl) {
    this.webClient = WebClient.create(baseUrl);
  }

  public Mono<User> getUser() {
    Mono<User> user = webClient.get().uri("/user").retrieve().bodyToMono(User.class);
    return user;
  }
  
  public Flux<User> getUsers() {
    return webClient.get()
        .uri("/users")
        .retrieve()
        .bodyToFlux(User.class);
  }
}
