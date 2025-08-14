package springtest.repository;

import springtest.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {
    Optional<User> findUserById(int id);

    List<User> findUsersByName(String name);

    void newUser(User user);

    void deleteUser(int id);

    int countUsers();

    void deleteUserByName(String name);

    int findNextId();
}
