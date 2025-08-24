package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.services.UserDTO;
import com.example.springbootdemo.model.services.UserService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<NewEntityResponse> createUser(
      @Valid @RequestBody NewEntityRequestBody body) {
    int userId = userService.createUser(body.getName(), body.getAge());
    return ResponseEntity.created(URI.create("/users/" + userId))
        .body(new NewEntityResponse(userId));
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable int id) {
    UserDTO userDTO = userService.getUserById(id);
    return new UserResponse(userDTO.getId(), userDTO.getName(), userDTO.getAge());
  }
}
