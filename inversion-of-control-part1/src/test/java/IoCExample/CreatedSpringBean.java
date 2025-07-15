package IoCExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CreatedSpringBean {
    private ApplicationContext context;

    @BeforeEach
    void setComponent() {
        context = new AnnotationConfigApplicationContext("IoCExample"); //-> Using base package
        // context = new AnnotationConfigApplicationContext(ApplicationConfig.class); // -> Using configuration class
    }

    @Test
    void createdInstance() {
        AnnotatedBeanComponent bean = context.getBean(AnnotatedBeanComponent.class);
        Assertions.assertNotNull(bean);
        String actual = bean.echo("Hello");
        Assertions.assertEquals("Hello", actual);
    }
}
