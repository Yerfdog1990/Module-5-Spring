package userregister.model.repository;

import userregister.model.entity.User;

public interface IUserRepository {
  User findByEmail(String email);

  User addNewUser(String email, String password);
}
