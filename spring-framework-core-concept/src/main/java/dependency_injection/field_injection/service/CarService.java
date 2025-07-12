package dependency_injection.field_injection.service;

import dependency_injection.field_injection.model.repository.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    // Field Injection using @Autowired
    @Autowired
    private Engine engine;

    public void drive() {
        engine.startEngine();
        System.out.println("Car is driving");
    }
}
