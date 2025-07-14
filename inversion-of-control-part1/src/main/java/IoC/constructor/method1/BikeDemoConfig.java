package IoC.constructor.method1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("IoC.constructor")
public class BikeDemoConfig {
    @Bean
    public Bike bike(BikeInfo info) {
        return new Bike(info.model(), info.color());
    }
}
