package reactordemo.reactorclient;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import webflux.pojo.User;
import webflux.undertow.ReactiveClientService;

public class ReactiveClientServiceTest {
  private ReactiveClientService reactiveClientService;
  private MockWebServer mockWebServer;

  @BeforeEach
  void setUp() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    reactiveClientService =
        new ReactiveClientService(String.format("http://localhost:%s", mockWebServer.getPort()));
  }

  @AfterEach
  void tearDown() throws IOException {
    mockWebServer.shutdown();
  }

  @Test
  void testGetUser() {
    MockResponse response =
        new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setBody("{\"id\":1,\"name\":\"John\",\"age\":30}");
    mockWebServer.enqueue(response);
    Mono<User> user = reactiveClientService.getUser();
    StepVerifier.create(user).expectNext(new User(1, "John", 30)).verifyComplete();
  }

  @Test
  void testGetUserWithDelay() {
    MockResponse response =
        new MockResponse()
            .setHeader("Content-Type", "application/json")
            .setResponseCode(200)
            .setBodyDelay(1000, TimeUnit.MILLISECONDS)
            .setBody("{\"id\":1,\"name\":\"John\",\"age\":30}");
    mockWebServer.enqueue(response);
    Mono<User> user = reactiveClientService.getUser();
    StepVerifier.create(user)
        .expectSubscription()
        .expectNoEvent(Duration.ofMillis(1000))
        .expectNext(new User(1, "John", 30))
        .verifyComplete();
  }

  @Test
  void testGetUserUsingFlux() {
    // Mock response with a JSON array of users
    String usersJson = """
        [
          {"id":1,"name":"John","age":30},
          {"id":2,"name":"Alice","age":25},
          {"id":3,"name":"Bob","age":35}
        ]
        """;
        
    MockResponse response = new MockResponse()
        .setHeader("Content-Type", "application/json")
        .setResponseCode(200)
        .setBody(usersJson);
        
    mockWebServer.enqueue(response);
    
    // Call the method under test
    Flux<User> users = reactiveClientService.getUsers();
    
    // Verify the results
    StepVerifier.create(users)
        .expectNext(new User(1, "John", 30))
        .expectNext(new User(2, "Alice", 25))
        .expectNext(new User(3, "Bob", 35))
        .verifyComplete();
  }

}
