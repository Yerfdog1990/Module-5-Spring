package IoC.nested_class_method;

public class SpringBeanService {
    public static class NestedClass {
        public void doSomething() {
            System.out.println("SpringBeanService doSomething");
        }
    }
}
