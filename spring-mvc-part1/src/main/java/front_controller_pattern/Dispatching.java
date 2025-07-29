package front_controller_pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Dispatching {
    private Benz benz;
    private Volkswagen volkswagen;

    public Dispatching() {
        this.benz = new Benz();
        this.volkswagen = new Volkswagen();
    }

    public void dispatch(String request) {
        if(request.equalsIgnoreCase(benz.getName())){
            System.out.println("Car specifications:");
            String benzSpecs = benz.toString();
            System.out.println(benzSpecs);
        } else if(request.equalsIgnoreCase(volkswagen.getName())){
            System.out.println("Car specifications:");
            String volkswagenSpecs = volkswagen.toString();
            System.out.println(volkswagenSpecs);
        } else{
            System.out.println("Sorry, we don't have this car.");
        }
    }
}
