package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.services.GreetingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello() {
        return greetingService.getGreeting();
    }
}
