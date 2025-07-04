package userregister.model.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import userregister.model.entity.User;

public class InMemoryUserRepository implements IUserRepository {
  private final Collection<User> users = new ArrayList<>();

  public List<User> findAll() {
    return List.copyOf(users);
  }

  @Override
  public User findByEmail(String email) {
    List<User> matchingUsers = users.stream().filter(em -> em.getEmail().equals(email)).toList();
    if (matchingUsers.size() > 1) {
      throw new IllegalStateException("More than one user with the same email");
    }
    if (matchingUsers.size() == 1) {
      return matchingUsers.getFirst();
    }
    return null;
  }

  @Override
  public User addNewUser(String email, String password) {
    User newUser = new User(email, password);
    users.add(newUser);
    System.out.printf("New user registered in-memory: %s%n", email);
    return newUser;
  }
}
