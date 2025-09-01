package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.dto.UserDTO;
import com.example.springbootdemo.model.services.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<NewEntityResponse> createUser(
            @Valid @RequestBody NewEntityRequestBody body) {
        log.info("Creating user: {}", body);
        int userId = userService.createUser(body.getName(), body.getAge());
        return ResponseEntity.created(URI.create("/users/" + userId))
                .body(new NewEntityResponse(userId));
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {
        log.info("Getting user with id: {}", id);
        UserDTO userDTO = userService.getUserById(id);
        return new UserResponse(userDTO.getId(), userDTO.getName(), userDTO.getAge());
    }
}
