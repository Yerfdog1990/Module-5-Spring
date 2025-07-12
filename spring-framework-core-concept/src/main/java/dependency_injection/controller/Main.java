package dependency_injection.controller;

import dependency_injection.model.repository.ICarRepository;
import dependency_injection.model.repository.InMemoryCarRepository;
import dependency_injection.service.CarService;

public class Main {
    public static void main(String[] args) {
        ICarRepository carRepository = new InMemoryCarRepository();
        CarService carService = new CarService(carRepository);
        carService.browseByModel("Honda Civic");
    }
}
