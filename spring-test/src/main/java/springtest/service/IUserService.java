package springtest.service;

import springtest.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(int id);

    int createNewUser(String name);

    void deleteUserById(int id);

    void deleteUserByName(String name);

    List<User> findUsersByName(String name);
}
