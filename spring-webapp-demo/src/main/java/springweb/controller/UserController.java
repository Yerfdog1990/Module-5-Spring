package springweb.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springweb.model.service.IUserService;
import springweb.model.service.UserDTO;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
  private final IUserService userService;

  @Autowired
  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<NewEntityResponse> postUser(@RequestBody UserRequestResponse userBody) {
    Integer id = userBody.getId();
    userService.createdUser(id, userBody.getName(), userBody.getAge());
    return ResponseEntity.created(URI.create(String.format("/users/%s", id)))
        .body(new NewEntityResponse(id));
  }

  @GetMapping("/{id}")
  @ResponseBody
  public ResponseEntity<UserRequestResponse> findUserById(@PathVariable("id") Integer id) {
    UserDTO userDTO = userService.findUserById(id);
    if (userDTO == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(new UserRequestResponse(userDTO.getId(), userDTO.getName(), userDTO.getAge()));
  }
}
