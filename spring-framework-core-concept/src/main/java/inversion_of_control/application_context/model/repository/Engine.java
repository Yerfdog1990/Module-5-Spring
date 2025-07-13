package inversion_of_control.application_context.model.repository;

public class Engine implements IEngine {
    @Override
    public void startEngine() {
        System.out.println("Engine is starting...");
    }
}
