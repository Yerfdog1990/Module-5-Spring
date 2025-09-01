package com.example.springbootdemo.model.repository;

import com.example.springbootdemo.model.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InMemoryUserRepository {
    List<UserEntity> findAll();

    Optional<UserEntity> findById(int id);

    UserEntity save(UserEntity userEntity);

    void deleteById(int id);
}
