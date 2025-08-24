package com.example.springbootdemo.model.services;

import com.example.springbootdemo.model.entity.Address;
import lombok.Data;

@Data
public class UserDTO {
  private final Integer id;
  private final String name;
  private final Integer age;
  private final Address address;
}
