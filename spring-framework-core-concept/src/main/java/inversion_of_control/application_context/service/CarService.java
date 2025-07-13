package inversion_of_control.application_context.service;

import inversion_of_control.application_context.model.repository.Engine;

public class CarService {
    private Engine engine;

    // Setter injection
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.startEngine();
        System.out.println("Car is driving...");
    }
}
