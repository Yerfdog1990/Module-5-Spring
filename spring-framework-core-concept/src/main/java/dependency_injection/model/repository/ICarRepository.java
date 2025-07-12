package dependency_injection.model.repository;

import dependency_injection.model.entity.Car;

import java.util.List;

@FunctionalInterface
public interface ICarRepository {
    List<Car> findAllCars();
}
