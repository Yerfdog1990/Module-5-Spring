package IoC.constructor_method;

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
