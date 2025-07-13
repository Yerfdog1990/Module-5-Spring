package IoC.nested_class;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NestedClassDemo {

    // Method to run logic
    public void run(ApplicationContext context) {
        SpringBeanService.NestedClass bean = context.getBean(SpringBeanService.NestedClass.class);
        bean.doSomething();
    }

    public static void main(String[] args) {
        // Load the application context from XML configuration
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config.xml");

        // Get the NestedClassDemo bean and invoke its functionality
        NestedClassDemo demo = ctx.getBean("demo", NestedClassDemo.class);
        demo.run(ctx);

        // Close the context
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
