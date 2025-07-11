package method3.model.service;

import method3.model.entity.Students;
import method3.model.repository.IStudentRepo;

public class StudentService {
  private final IStudentRepo studentRepository;

  public StudentService(IStudentRepo studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void registerStudent(String name, String email) {
    Students studentByEmail = studentRepository.getStudentByEmail(email);
    if (studentByEmail != null) {
      throw new IllegalStateException("Email already exists");
    } else {
      studentRepository.addStudent(name, email);
      System.out.printf(
          "Student with name %s and email %s has been registered successfully.", name, email);
    }
  }
}
