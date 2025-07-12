package dependency_injection.setter_injection.controller;

import dependency_injection.setter_injection.model.repository.Engine;
import dependency_injection.setter_injection.service.CarService;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();   // Dependency created externally
        CarService car = new CarService(); // CarService object created first
        car.setEngine(engine);          // Injected via setter
        car.drive();
    }
}
