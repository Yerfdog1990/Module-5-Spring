package com.example.springbootdemo.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
@Valid
@Data
public class AppConfig {
  @NotBlank private String attribute1;
  @NotNull private Attributes2 attribute2;

  @Data
  @Valid
  static class Attributes2 {
    @NotNull private String nestedAtt1;
    private String nestedAtt2;
  }
}
