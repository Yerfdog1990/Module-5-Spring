package method2.model.service;

import method2.model.entity.Students;
import method2.model.repository.IStudentRepository;
import org.springframework.stereotype.Service;

@Service
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
