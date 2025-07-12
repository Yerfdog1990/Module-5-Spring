package dependency_injection.constructor_injection.model.repository;

import dependency_injection.constructor_injection.model.entity.Car;

import java.util.List;

@FunctionalInterface
public interface ICarRepository {
    List<Car> findAllCars();
}
