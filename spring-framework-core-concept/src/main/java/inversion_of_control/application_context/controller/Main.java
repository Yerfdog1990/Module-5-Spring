package inversion_of_control.application_context.controller;

import inversion_of_control.application_context.service.CarService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        CarService car = (CarService) context.getBean("car");
        car.drive();
    }
}
