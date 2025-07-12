package dependency_injection.controller;

import dependency_injection.model.repository.ICarRepository;
import dependency_injection.model.repository.InMemoryCarRepository;
import dependency_injection.service.CarService;

public class Main {
    public static void main(String[] args) {
        ICarRepository carRepository = new InMemoryCarRepository(); // Dependency created externally
        CarService carService = new CarService(carRepository); // Injected via constructor
        carService.browseByModel("Honda Civic");
    }
}
