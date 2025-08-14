package springtest.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springtest.entity.User;
import springtest.service.IUserService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        Optional<User> userOptional = userService.getUserById(id);

        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NewUserResponse> postNewUser(@RequestBody NewUserRequest newUser) {
        int newUserId = userService.createNewUser(newUser.getName());
        return ResponseEntity.created(URI.create("/users/" + newUserId))
                .body(new NewUserResponse(newUserId));
    }
}
