package com.example.springbootdemo.model.services;

import com.example.springbootdemo.model.entity.UserEntity;
import com.example.springbootdemo.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BadUserService {
  private final UserRepository userRepository;

  public BadUserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional(readOnly = true)
  public UserEntity getUserById(int id) {
    return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
  }
}
