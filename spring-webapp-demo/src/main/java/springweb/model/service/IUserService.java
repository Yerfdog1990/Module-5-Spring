package springweb.model.service;

public interface IUserService {
  void createdUser(Integer id, String name, Integer age);

  UserDTO findUserById(Integer id);
}
