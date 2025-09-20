package rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import rest.api.model.entity.User;
import rest.api.model.repository.UserRepo;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class APIController {
    private final UserRepo userRepo;
    @Autowired
    public APIController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @GetMapping("/")
    public String getPage(){
        return "<h1>Hello, welcome to my first CRUD RESTFul API demo.</h1>";
    }

    // Create a new user
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userRepo.save(new User(user.getName(), user.getEmail(), user.getPassword(), user.getRole()));
    }

    // Read (Get) all users
    @GetMapping("readall")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Read (Get) a single user by ID
    @GetMapping("readbyid/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        return userRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a user (supports partial updates)
    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User userDetails) {
        return userRepo.findById(id)
                .map(existingUser -> {
                    // Only update fields that are provided in the request (non-null)
                    if (userDetails.getName() != null) {
                        existingUser.setName(userDetails.getName());
                    }
                    if (userDetails.getEmail() != null) {
                        existingUser.setEmail(userDetails.getEmail());
                    }
                    if (userDetails.getPassword() != null) {
                        existingUser.setPassword(userDetails.getPassword());
                    }
                    if (userDetails.getRole() != null) {
                        existingUser.setRole(userDetails.getRole());
                    }
                    return ResponseEntity.ok(userRepo.save(existingUser));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a user
    @DeleteMapping("delete/{id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        return userRepo.findById(id)
                .map(user -> {
                    userRepo.delete(user);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
