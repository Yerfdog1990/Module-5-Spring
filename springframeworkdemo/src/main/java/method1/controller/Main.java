package method1.controller;

import method1.model.repository.IStudentRepository;
import method1.model.repository.StudentRepository;
import method1.model.service.StudentService;

public class Main {
  public static void main(String[] args) {
    IStudentRepository studentRepository = new StudentRepository();
    StudentService studentService = new StudentService(studentRepository);
    studentService.registerStudent("John", "john@example.com");
  }
}
