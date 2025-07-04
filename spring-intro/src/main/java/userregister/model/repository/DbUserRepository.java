package userregister.model.repository;

import userregister.model.entity.User;

public class DbUserRepository implements IUserRepository {
  private final String onlyUserEmail;
  private final User onlyUser;

  // Constructor
  public DbUserRepository() {
    onlyUserEmail = "joe@example.com";
    onlyUser = new User(onlyUserEmail, "Random password");
  }

  @Override
  public User findByEmail(String email) {

    System.out.println("Pretending that we are querying the database to retrieve users\n");
    if (email.equals(onlyUserEmail)) {
      System.out.printf("User found by %s%n", email);
      return onlyUser;
    }
    System.out.printf("%s does not exist%n", email);
    return null;
  }

  @Override
  public User addNewUser(String email, String password) {
    System.out.printf("Pretending that we are saving %s to the database%n", email);
    return new User(email, password);
  }
}
