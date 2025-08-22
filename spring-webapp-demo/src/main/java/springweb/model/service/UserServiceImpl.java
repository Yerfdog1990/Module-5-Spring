package springweb.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import springweb.model.dao.IUserRepository;
import springweb.model.entity.User;

@Service
public class UserServiceImpl implements IUserService {
  private final IUserRepository userRepository;

  public UserServiceImpl(IUserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public void createdUser(Integer id, String name, Integer age) {
    userRepository.createUser(new User(id, name, age));
  }

  @Override
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public UserDTO findUserById(Integer id) {
    User user = userRepository.findUserById(id);
    if (user == null) {
      return null;
    }
    return new UserDTO(user.getId(), user.getName(), user.getAge());
  }
}
