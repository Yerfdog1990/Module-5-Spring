package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.entity.UserEntity;
import com.example.springbootdemo.model.services.BadUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/evil-user")
public class BadUserController {
  private final BadUserService badUserService;

  public BadUserController(BadUserService badUserService) {
    this.badUserService = badUserService;
  }

  @GetMapping("/{id}")
  public String getBadUser(@PathVariable Integer id, Model model) {
    UserEntity userEntity = badUserService.getUserById(id);
    model.addAttribute("userEntity", userEntity);
    return "baduser";
  }
}
