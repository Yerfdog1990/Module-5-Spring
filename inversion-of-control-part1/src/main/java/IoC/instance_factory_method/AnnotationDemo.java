package IoC.instance_factory_method;

import model.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public abstract class AnnotationDemo extends Demo{
    @Override
    protected ApplicationContext context() {
        return new AnnotationConfigApplicationContext(AppConfig.class);
    }
    protected abstract <T> Class<T> configClass();
}
