package method2.model.repository;

import method2.model.entity.Students;

public interface IStudentRepository {
  void addStudent(String name, String email);

  Students getStudentByEmail(String email);
}
