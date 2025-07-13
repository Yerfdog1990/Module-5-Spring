package IoC.instance_factory_method;

import model.AppConfig;
import org.springframework.context.ApplicationContext;

public class AnnotatedBeanFactoryMethodDemo extends AnnotationDemo{
    @Override
    protected Class<AppConfig> configClass() {
        return AppConfig.class;
    }

    @Override
    protected void doSomething(ApplicationContext context) {
        SomeService bean = context.getBean(SomeService.class);
        bean.doSomething();
    }

    // Main method
    public static void main(String[] args) {
        AnnotatedBeanFactoryMethodDemo beanFactory = new AnnotatedBeanFactoryMethodDemo();
        beanFactory.run();
    }
}
