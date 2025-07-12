package dependency_injection.setter_injection.model.repository;

public class Engine implements IEngine {
    @Override
    public void start() {
        System.out.println("Engine starting...");
    }
}
