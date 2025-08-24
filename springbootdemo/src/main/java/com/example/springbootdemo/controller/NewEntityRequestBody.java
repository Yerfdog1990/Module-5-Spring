package com.example.springbootdemo.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewEntityRequestBody {
  @NotNull
  @NotBlank
  @Size(min = 3, max = 10)
  private final String name;

  @NotNull @Positive private final Integer age;
}
