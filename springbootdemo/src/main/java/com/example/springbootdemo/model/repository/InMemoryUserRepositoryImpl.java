package com.example.springbootdemo.model.repository;

import com.example.springbootdemo.model.entity.UserEntity;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@Profile({"dev", "default"})
public class InMemoryUserRepositoryImpl implements InMemoryUserRepository {
    private final Map<Integer, UserEntity> usersDb = new HashMap<>();

    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<UserEntity> findById(int id) {
        System.out.printf("Finding user with id %d%n", id);
        return Optional.ofNullable(usersDb.get(id));
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        System.out.printf("Saving user %s%n", userEntity);
        int nextId = updateIdSequence();
        userEntity.setId(nextId);
        usersDb.put(nextId, userEntity);
        return userEntity;
    }

    @Override
    public void deleteById(int id) {

    }

    private int updateIdSequence() {
        // 1. Find the maximum id in the current users set
        Stream<Integer> idStream = usersDb.keySet().stream();
        Integer maxId = idStream.reduce(0, (i1, i2) -> i1 > i2 ? i1 : i2);

        // First iteration: 0, 1, -> 0 + 1 = 1
        // Second iteration: 1, 2, -> 1 + 2 = 3
        // Third iteration: 3, 3, -> 3 + 3 = 6

        // 2. Return the next integer to that maximum value

        return maxId + 1;
    }
}
