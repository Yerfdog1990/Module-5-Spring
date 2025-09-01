package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.services.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {
    @LocalServerPort
    private int localhostPort;

    private WebTestClient webTestClient;

    @MockBean
    private GreetingService greetingService;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + localhostPort)
                .build();
    }

    @Test
    void testHello() {
        // Configure the service mock
        String mockedService = "Hello from a mocked service";
        Mockito.when(greetingService.getGreeting()).thenReturn(mockedService);

        // Perform the GET request
        webTestClient.get().uri("/greeting/hello")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(mockedService);
    }
}
