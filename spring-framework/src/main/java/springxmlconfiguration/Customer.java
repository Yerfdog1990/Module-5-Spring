package springxmlconfiguration;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Customer {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext applicationContext =
        new ClassPathXmlApplicationContext("bean.xml");
    Employee employee = (Employee) applicationContext.getBean("employee");
    employee.display();
    System.out.println(employee);
  }
}
