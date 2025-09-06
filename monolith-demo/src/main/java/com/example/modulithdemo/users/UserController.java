package com.example.modulithdemo.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<NewUserResponse> createUser(@RequestBody NewUserRequest userRequest){
        int newUserId = userService.createUser(userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.created(URI.create("/users/" + newUserId)).body(new NewUserResponse(newUserId));
    }
    @GetMapping
    public Collection<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }
}
