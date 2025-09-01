package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ConfigController {
  private final AppConfig appConfig;
  private final String constant;

  @Autowired
  public ConfigController(AppConfig appConfig, @Value("${app.myConstant1}") String constant) {
    this.appConfig = appConfig;
    this.constant = constant;
  }

  @GetMapping("/config")
  public AppConfig getAppConfig() {
    return appConfig;
  }

  @GetMapping(path = "/constant", produces = MediaType.APPLICATION_JSON_VALUE)
  public String retrieveConstant() {
    return constant;
  }
}
