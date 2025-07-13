package IoC.constructor_method;

import IoC.instance_factory_method.AnnotationDemo;
import org.springframework.context.ApplicationContext;

public class BikeAnnotationDemo extends AnnotationDemo {
    @Override
    protected Class<BikeDemoConfig> configClass() {
        return BikeDemoConfig.class;
    }

    @Override
    protected void doSomething(ApplicationContext context) {
        Bike bean = context.getBean(Bike.class);
        System.out.println(bean);
    }
    // Main class
    public static void main(String[] args) {
        BikeAnnotationDemo bikeDemo = new BikeAnnotationDemo();
        bikeDemo.run();
    }
}
