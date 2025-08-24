package com.example.springbootdemo.model.services;

import com.example.springbootdemo.model.entity.Address;
import com.example.springbootdemo.model.entity.UserEntity;
import com.example.springbootdemo.model.pojo.UserPOJO;
import com.example.springbootdemo.model.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {
  private final UserRepository userRepository;

  UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserPOJO getUserPOJO() {
    return new UserPOJO(1, "Jane", 50);
  }

  @Transactional
  @Override
  public int createUser(String name, Integer age) {
    UserEntity entity = new UserEntity(name, age);
    entity.setAddress(new Address("123 Main St"));
    UserEntity savedUser = userRepository.save(entity);
    return savedUser.getId();
  }

  @Override
  @Transactional(readOnly = true)
  public UserDTO getUserById(int id) {
    return userRepository
        .findById(id)
        .map(
            userEntity ->
                new UserDTO(
                    userEntity.getId(),
                    userEntity.getName(),
                    userEntity.getAge(),
                    userEntity.getAddress()))
        .orElseThrow(() -> new RuntimeException("User with id " + id + " not found."));
  }
}
