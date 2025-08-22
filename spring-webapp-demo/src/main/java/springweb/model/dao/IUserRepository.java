package springweb.model.dao;

import springweb.model.entity.User;

public interface IUserRepository {
  void createUser(User user);

  User findUserById(Integer id);
}
