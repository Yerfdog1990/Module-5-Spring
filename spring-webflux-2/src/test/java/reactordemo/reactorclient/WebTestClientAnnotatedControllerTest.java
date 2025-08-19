package reactordemo.reactorclient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import webflux.controller.NettyController;

public class WebTestClientAnnotatedControllerTest {
  private WebTestClient webTestClient;

  @BeforeEach
  void setUp() {
    webTestClient =
        WebTestClient.bindToController(new NettyController())
            .configureClient()
            .baseUrl("http://localhost:8080")
            .build();
  }

  @Test
  void testAnnotatedController() {
    webTestClient
        .get()
        .uri("/netty")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.TEXT_PLAIN + ";charset=UTF-8"
        )
        .expectBody(String.class)
        .isEqualTo("Hello from Netty!");
  }
}
