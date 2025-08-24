package com.example.springbootdemo.controller;

import lombok.Data;

@Data
public class UserResponse {
  private final Integer id;
  private final String name;
  private final Integer age;
}
