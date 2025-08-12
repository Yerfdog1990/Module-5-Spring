package springtest.service;

import springtest.entity.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(int id);
}
