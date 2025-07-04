package userregister;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import userregister.model.service.UserService;

public class Main {
  public static void main(String[] args) {
    //ApplicationContext context = new AnnotationConfigApplicationContext("userregister");
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    UserService userService = context.getBean(UserService.class);
    try {
      userService.registerUser("joe@example.com", "password");
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }
}
