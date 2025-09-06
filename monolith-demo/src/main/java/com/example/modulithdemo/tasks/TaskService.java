package com.example.modulithdemo.tasks;

import com.example.modulithdemo.users.UserService;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final UserService userService;

    public TaskService(UserService userService) {
        this.userService = userService;
    }
    public String greet(String greeting) {
        return greeting;
    }
}
