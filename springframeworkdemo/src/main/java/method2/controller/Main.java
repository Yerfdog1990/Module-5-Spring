package method2.controller;

import method2.model.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
  public static void main(String[] args) {
    //    IStudentRepository studentRepository = new StudentRepository();
    //    StudentService studentService = new StudentService(studentRepository);
    ApplicationContext context = new AnnotationConfigApplicationContext("method2");
    StudentService bean = context.getBean(StudentService.class);
    bean.registerStudent("John", "john@example.com");
  }
}
