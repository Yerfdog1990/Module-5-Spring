package dependency_injection.model.repository;

import dependency_injection.model.entity.Car;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryCarRepository implements ICarRepository{
    List<Car> cars = new ArrayList<>();

    @Override
    public List<Car> findAllCars() {
        Collection<Car> allCars = createCarCollection();
        return (List<Car>) allCars;
    }

    public Collection<Car> createCarCollection() {
        Car benz = new Car();
        benz.setColor("Brown");
        benz.setYear(2024);
        benz.setModel("Mercedes Benz GLE 300");
        benz.setEngine(3000);
        cars.add(benz);

        Car toyota = new Car();
        toyota.setColor("Silver");
        toyota.setYear(2024);
        toyota.setModel("Toyota Camry");
        toyota.setEngine(2500);
        cars.add(toyota);

        Car mazda = new Car();
        mazda.setColor("Red");
        mazda.setYear(2024);
        mazda.setModel("Mazda CX-5");
        mazda.setEngine(2500);
        cars.add(mazda);

        Car jeep = new Car();
        jeep.setColor("Black");
        jeep.setYear(2024);
        jeep.setModel("Jeep Wrangler");
        jeep.setEngine(3600);
        cars.add(jeep);

        Car honda = new Car();
        honda.setColor("White");
        honda.setYear(2024);
        honda.setModel("Honda Civic");
        honda.setEngine(2000);
        cars.add(honda);

        Car honda2 = new Car();
        honda2.setColor("Black");
        honda2.setYear(2023);
        honda2.setModel("Honda Civic");
        honda2.setEngine(2000);
        cars.add(honda2);

        Car honda3 = new Car();
        honda3.setColor("Red");
        honda3.setYear(2022);
        honda3.setModel("Honda Civic");
        honda3.setEngine(2000);
        cars.add(honda3);

        Car honda4 = new Car();
        honda4.setColor("Blue");
        honda4.setYear(2021);
        honda4.setModel("Honda Civic");
        honda4.setEngine(2000);
        cars.add(honda4);

        Car honda5 = new Car();
        honda5.setColor("Silver");
        honda5.setYear(2020);
        honda5.setModel("Honda Civic");
        honda5.setEngine(2000);
        cars.add(honda5);

        return cars;
    }
}
