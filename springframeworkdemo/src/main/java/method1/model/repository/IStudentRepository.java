package method1.model.repository;

import method1.model.entity.Students;

public interface IStudentRepository {
  void addStudent(String name, String email);

  Students getStudentByEmail(String email);
}
