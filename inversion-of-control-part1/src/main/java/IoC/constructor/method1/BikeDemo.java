package IoC.constructor.method1;

import IoC.instance_factory_method.XmlDemo;
import org.springframework.context.ApplicationContext;

public class BikeDemo extends XmlDemo {
    @Override
    protected void doSomething(ApplicationContext context) {
        Bike bean = context.getBean(Bike.class);
        System.out.println(bean);
    }
    // Main class
    public static void main(String[] args) {
        BikeDemo bikeDemo = new BikeDemo();
        bikeDemo.run();
    }
}
