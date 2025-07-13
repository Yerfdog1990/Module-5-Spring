package IoC.instance_factory_method;

public class TruckFactory {
    public Truck createTruck(String model, String color) {
        return new Truck(model, color);
    }
}
