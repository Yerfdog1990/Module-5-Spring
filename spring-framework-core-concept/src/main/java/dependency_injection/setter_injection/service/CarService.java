package dependency_injection.setter_injection.service;

import dependency_injection.setter_injection.model.repository.Engine;

public class CarService {
    private Engine engine;

    // Setter Injection
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        if (engine != null) {
            engine.start();
            System.out.println("Car is driving");
        } else {
            System.out.println("No engine found!");
        }
    }
}
