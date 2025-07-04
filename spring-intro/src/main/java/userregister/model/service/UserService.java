package userregister.model.service;

import userregister.model.entity.User;
import userregister.model.repository.IUserRepository;

//@Service
public class UserService {
  private final IUserRepository userRepository;

  // Constructor
  public UserService(IUserRepository userRepository) {
    if (userRepository == null) {
      throw new IllegalArgumentException("UserRepository cannot be null");
    }
    this.userRepository = userRepository;
  }

  public void registerUser(String email, String password) {
    // 1. Check that this user is not already registered
    User existingUser = userRepository.findByEmail(email);
    if (existingUser != null) {
      throw new IllegalStateException("User with this email already exists");
    }
    // 2. Validation
    // TODO Validate email and password strength

    // 3. Register the new user
    User registeredUser = userRepository.addNewUser(email, password);
    System.out.printf("%s registered successfully%n", registeredUser);
  }
}
