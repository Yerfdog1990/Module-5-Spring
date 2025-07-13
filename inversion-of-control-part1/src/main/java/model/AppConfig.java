package model;

import IoC.instance_factory_method.SomeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("IoC")
public class AppConfig {
    @Bean
    public SomeService someService(){
        return () -> System.out.println("SomeService doSomething");
    }
}
