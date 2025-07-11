package method1.model.service;

import method1.model.entity.Students;
import method1.model.repository.IStudentRepository;

public class StudentService {
  private final IStudentRepository studentRepository;

  public StudentService(IStudentRepository studentRepository) {
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
