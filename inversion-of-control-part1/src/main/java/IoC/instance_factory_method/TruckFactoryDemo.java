package IoC.instance_factory_method;

import org.springframework.context.ApplicationContext;

public class TruckFactoryDemo extends XmlDemo{
    @Override
    protected void doSomething(ApplicationContext context) {
        TruckFactory bean = context.getBean(TruckFactory.class);
        System.out.println(bean);
    }
}
