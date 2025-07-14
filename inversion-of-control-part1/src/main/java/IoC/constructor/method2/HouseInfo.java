package IoC.constructor.method2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class HouseInfo {
    public String model(){
        return "Storey building";
    }
    public String color(){
        return "White";
    }
}
