package IoC.instance_factory_method;

import IoC.nested_class_method.SpringBeanService;
import org.springframework.context.ApplicationContext;

public class NestedClass extends XmlDemo{
    @Override
    protected void doSomething(ApplicationContext context) {
        SpringBeanService.NestedClass bean = context.getBean(SpringBeanService.NestedClass.class);
        bean.doSomething();
    }

    // Main method
    public static void main(String[] args) {
        NestedClass nestedClass = new NestedClass();
        nestedClass.run();
    }
}
