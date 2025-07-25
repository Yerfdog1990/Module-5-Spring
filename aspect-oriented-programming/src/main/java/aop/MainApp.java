package aop;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IShop shop = context.getBean(IShop.class);
        shop.addItem("Apple", 1.2);
        shop.addItem("Banana", 1.5);
        shop.addItem("Orange", 1.1);
        shop.removeItem("Apple");
        shop.getPrice("Banana");
    }
}
