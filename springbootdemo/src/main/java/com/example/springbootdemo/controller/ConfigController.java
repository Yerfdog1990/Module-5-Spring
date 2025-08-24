package com.example.springbootdemo.controller;

import com.example.springbootdemo.config.AppConfig;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ConfigController {
  private final AppConfig appConfig;

  public ConfigController(AppConfig appConfig) {
    this.appConfig = appConfig;
  }

  @GetMapping("/config")
  public AppConfig getAppConfig() {
    return appConfig;
  }
}
