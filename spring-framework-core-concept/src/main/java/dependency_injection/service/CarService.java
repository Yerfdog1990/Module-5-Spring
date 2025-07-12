package dependency_injection.service;

import dependency_injection.model.entity.Car;
import dependency_injection.model.repository.ICarRepository;

import java.util.List;

public class CarService {
    private final ICarRepository carRepository;

    public CarService(ICarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> browseByColor(String color) {
        List<Car> matchingCars = carRepository.findAllCars().stream().filter(c -> c.getColor().equals(color)).toList();
        if(matchingCars == null || matchingCars.isEmpty()){
            System.err.println("Car color cannot be empty or null");
        }else if(matchingCars.size() == 1){
            System.out.println("Found 1 car with color: " + color);
            System.out.println("Car: ");
            matchingCars.forEach(System.out::println);
        }else if(matchingCars.size() > 1){
            System.out.println("Found " + matchingCars.size() + " cars with color: " + color);
            System.out.println("Car list: ");
            matchingCars.forEach(System.out::println);
        }
        return matchingCars;
    }

    public List<Car> browseByYear(int year) {
        List<Car> matchingCars = carRepository.findAllCars().stream().filter(c -> c.getYear() == year).toList();
        if(matchingCars == null || matchingCars.isEmpty()){
            System.err.println("Car year cannot be empty or null");
        } else if(matchingCars.size() == 1){
            System.out.println("Found 1 car with year: " + year);
            System.out.println("Car: ");
            matchingCars.forEach(System.out::println);
        } else if(matchingCars.size() > 1){
            System.out.println("Found " + matchingCars.size() + " cars with year: " + year);
            System.out.println("Car list: ");
            matchingCars.forEach(System.out::println);
        }
        return matchingCars;
    }

    public List<Car> browseByModel(String model) {
        List<Car> matchingCars = carRepository.findAllCars().stream().filter(c -> c.getModel().equals(model)).toList();
        if(matchingCars == null || matchingCars.isEmpty()){
            System.err.println("Car model cannot be empty or null");
        } else if(matchingCars.size() == 1){
            System.out.println("Found 1 car with model: " + model);
            System.out.println("Car: ");
            matchingCars.forEach(System.out::println);
        } else if(matchingCars.size() > 1){
            System.out.println("Found " + matchingCars.size() + " cars with model: " + model);
            System.out.println("Car list: ");
            matchingCars.forEach(System.out::println);
        }
        return matchingCars;
    }

    public List<Car> browseByEngine(String engine) {
        List<Car> matchingCars = carRepository.findAllCars().stream().filter(c -> c.getEngine() == Integer.parseInt(engine)).toList();
        if(matchingCars == null || matchingCars.isEmpty()){
            System.err.println("Car engine cannot be empty or null");
        } else if(matchingCars.size() == 1){
            System.out.println("Found 1 car with engine: " + engine);
            System.out.println("Car: ");
            matchingCars.forEach(System.out::println);
        } else if(matchingCars.size() > 1){
            System.out.println("Found " + matchingCars.size() + " cars with engine: " + engine);
            System.out.println("Car list: ");
            matchingCars.forEach(System.out::println);
        }
        return matchingCars;
    }
}
