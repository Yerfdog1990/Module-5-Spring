package springmvcdemo.controller;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
  private final MessageSource messageSource;

  public UserController(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String sayHi() {
    return "Hi!";
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "/greet",
      produces = MediaType.TEXT_PLAIN_VALUE)
  public String greet() {
    return "Hello World For Get Request!";
  }

  @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String greetFromPost() {
    return "Hello World For POST Request!";
  }

  @GetMapping(value = "/static-user", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getStaticUser() {
    return new User("John Doe", 23, "Very tired");
  }

  @PostMapping("processed-user")
  public User processUser(@RequestBody User inputUser) {
    return new User(
        inputUser.getName() + " processed",
        inputUser.getAge() + 1,
        "Very " + inputUser.getMaritalStatus());
  }

  @GetMapping("user-factory")
  public User createUser(
      @RequestParam("name") String name,
      @RequestParam("age") int age,
      @RequestParam("maritalStatus") String maritalStatus) {
    return new User(name, age, maritalStatus);
  }

  @GetMapping("i18n")
  public String greetFromBundle(@RequestHeader("Accept-Language") String lang) {
    return messageSource.getMessage("greeting", null, new Locale(lang));
  }
}
