package method3.controller;

import method3.model.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    StudentService bean = context.getBean(StudentService.class);
    bean.registerStudent("John", "john@example.com");
  }
}
