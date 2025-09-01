package com.example.springbootdemo.model.repository;

import com.example.springbootdemo.model.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("prod")
public class UserEntityJpaRepository implements InMemoryUserRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public UserEntityJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<UserEntity> findAll() {
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(int id) {
        return Optional.empty();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }
}
