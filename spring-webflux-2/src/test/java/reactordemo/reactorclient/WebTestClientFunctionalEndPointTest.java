package reactordemo.reactorclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import webflux.config.WebFluxConfig;
import webflux.pojo.User;

public class WebTestClientFunctionalEndPointTest {
  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    webTestClient =
        WebTestClient.bindToRouterFunction(new WebFluxConfig().routerFunction())
            .configureClient()
            .build();
  }

  @Test
  void testUserEndpoint() {
    webTestClient
        .get()
        .uri("/user")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON)
        .expectBody(User.class)
        .isEqualTo(new User(1, "John", 30));
  }
}
