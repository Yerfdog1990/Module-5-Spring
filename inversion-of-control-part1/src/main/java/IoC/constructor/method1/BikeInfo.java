package IoC.constructor.method1;

import org.springframework.stereotype.Component;

@Component
public class BikeInfo {
    public String model(){
        return "Boxer";
    }
    public String color(){
        return "Red";
    }
}
