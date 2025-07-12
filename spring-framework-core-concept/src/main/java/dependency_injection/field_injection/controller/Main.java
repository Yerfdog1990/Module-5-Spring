package dependency_injection.field_injection.controller;

import dependency_injection.field_injection.service.CarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "dependency_injection.field_injection")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        CarService car = context.getBean(CarService.class);
        car.drive();
    }
}
