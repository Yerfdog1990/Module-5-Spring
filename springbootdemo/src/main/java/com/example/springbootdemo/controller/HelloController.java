package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.pojo.UserPOJO;
import com.example.springbootdemo.model.services.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private final IUserService userService;

  public HelloController(IUserService userService) {
    this.userService = userService;
  }

  @GetMapping(path = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  public String hello() {
    return "Hello World from Spring Boot!";
  }

  @GetMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserPOJO getUser() {
    return new UserPOJO(1, "John", 20);
  }

  @GetMapping(path = "/user-pojo", produces = MediaType.APPLICATION_JSON_VALUE)
  public UserPOJO getUserFromPOJO() {
    UserPOJO userPOJO = userService.getUserPOJO();
    return userPOJO;
  }
}
