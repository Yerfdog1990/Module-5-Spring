package userregister.appconfig;

import userregister.model.repository.DbUserRepository;
import userregister.model.repository.IUserRepository;
import userregister.model.repository.InMemoryUserRepository;

// @Configuration
public class AppConfig {

  // @Bean
  public IUserRepository userRepository() {
    // Use an in-memory approach if the system property repository.approach = 'memory'
    String approachPropValue = System.getProperty("repository.approach");
    IUserRepository userRepository;
    if (approachPropValue != null && approachPropValue.equals("memory")) {
      userRepository = new InMemoryUserRepository();
    } else {
      userRepository = new DbUserRepository();
    }
    return userRepository;
  }
}
