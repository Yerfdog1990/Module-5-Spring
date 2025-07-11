package method3.model.repository;

import method3.model.entity.Students;

public interface IStudentRepo {
  void addStudent(String name, String email);

  Students getStudentByEmail(String email);
}
