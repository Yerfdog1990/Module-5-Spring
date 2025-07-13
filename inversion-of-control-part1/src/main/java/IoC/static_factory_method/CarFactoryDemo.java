package IoC.static_factory_method;

import IoC.instance_factory_method.XmlDemo;
import org.springframework.context.ApplicationContext;

public class CarFactoryDemo extends XmlDemo {
    @Override
    protected void doSomething(ApplicationContext context) {
        Car bean = context.getBean(Car.class);
        System.out.println(bean);
    }
    // Main method
    public static void main(String[] args) {
        CarFactoryDemo carFactory = new CarFactoryDemo();
        carFactory.run();
    }
}
