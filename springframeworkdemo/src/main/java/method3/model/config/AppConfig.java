package method3.model.config;

import method3.model.repository.DbStudentRepo;
import method3.model.repository.IStudentRepo;
import method3.model.repository.InMemoryStudentRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public IStudentRepo studentRepository() {
    // Use an in-memory approach if the system property repository.approach=memory
    String db = System.getProperty("repository.approach");
    IStudentRepo studentRepo;
    if (db == null && db.equals("memory")) {
      studentRepo = new InMemoryStudentRepo();
    } else {
      studentRepo = new DbStudentRepo();
    }
    return studentRepo;
  }
}
