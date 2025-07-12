package dependency_injection.field_injection.model.repository;

import org.springframework.stereotype.Repository;

@Repository
public class Engine implements IEngine {
    @Override
    public void startEngine() {
        System.out.println("Starting engine ...");
    }
}
