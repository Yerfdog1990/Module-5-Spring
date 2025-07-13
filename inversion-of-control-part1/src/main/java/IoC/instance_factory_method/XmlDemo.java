package IoC.instance_factory_method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class XmlDemo extends Demo{
    @Override
    protected ApplicationContext context() {
        return new ClassPathXmlApplicationContext("spring-config.xml");
    }
}
