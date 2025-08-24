package com.example.springbootdemo.model.services;

import com.example.springbootdemo.model.pojo.UserPOJO;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {
  UserPOJO getUserPOJO();

  @Transactional
  int createUser(String name, Integer age);

  UserDTO getUserById(int id);
}
