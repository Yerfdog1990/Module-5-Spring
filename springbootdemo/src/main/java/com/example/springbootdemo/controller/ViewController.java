package com.example.springbootdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
  @GetMapping(path = "/view", produces = MediaType.TEXT_PLAIN_VALUE)
  public String helloFromView(Model model) {
    model.addAttribute("message", "Hello from View!");
    return "index";
  }
}
