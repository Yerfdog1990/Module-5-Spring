package IoC.static_factory_method;

public class CarFactory {
    public static Car createCar(String model, String color) {
        return new Car(model, color);
    }
}
