package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.services.GreetingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GreetingService greetingService;

    @TestConfiguration
    static class TestingConfig {
        @Bean
        public GreetingService greetingService() {
            return Mockito.mock(GreetingService.class);
        }
    }

    @Test
    void helloShouldReturnGreetingService() throws Exception {
        // Configure the mock service to return a predefined greeting
        String expectedContent = "Hello from a mocked service";
        Mockito.when(greetingService.getGreeting()).thenReturn(expectedContent);

        // Perform GET request to the hello endpoint
        mockMvc.perform(MockMvcRequestBuilders.get("/greeting/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        expectedContent));
    }
}
