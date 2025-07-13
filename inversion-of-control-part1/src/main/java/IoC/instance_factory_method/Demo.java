package IoC.instance_factory_method;

import org.springframework.context.ApplicationContext;

abstract class Demo {
    protected abstract ApplicationContext context();
    protected abstract void doSomething(ApplicationContext context);
    public void run() {
        doSomething(context());
    }
}
