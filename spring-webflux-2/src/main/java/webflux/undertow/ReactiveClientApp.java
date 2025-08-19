package webflux.undertow;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import webflux.pojo.User;

public class ReactiveClientApp {
  public static void main(String[] args) throws InterruptedException {
    WebClient client = WebClient.create("http://localhost:8080");

    Mono<String> response = client.get().uri("/undertow").retrieve().bodyToMono(String.class);
    response.subscribe(System.out::println);
    Mono<User> user = client.get().uri("/user").retrieve().bodyToMono(User.class);
    user.subscribe(System.out::println);

    Thread.sleep(1000);
    System.out.println("Client app finished");
  }
}
