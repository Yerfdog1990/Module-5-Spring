package IoC.constructor.method2;

import IoC.instance_factory_method.XmlDemo;
import org.springframework.context.ApplicationContext;

public class HouseDemo extends XmlDemo {
    @Override
    protected void doSomething(ApplicationContext context) {
        House bean = context.getBean(House.class);
        System.out.println(bean);
    }
    // Main method
    public static void main(String[] args) {
        HouseDemo houseDemo = new HouseDemo();
        houseDemo.run();
    }
}
