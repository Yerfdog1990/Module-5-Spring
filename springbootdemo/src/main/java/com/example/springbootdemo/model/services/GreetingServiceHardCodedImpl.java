package com.example.springbootdemo.model.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceHardCodedImpl implements GreetingService {
    @Override
    public String getGreeting() {
        return "Hello from a hardcoded service";
    }
}
