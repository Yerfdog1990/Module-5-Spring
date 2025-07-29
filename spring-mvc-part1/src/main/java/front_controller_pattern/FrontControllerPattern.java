package front_controller_pattern;

public class FrontControllerPattern {
    public static void main(String[] args) {
        FrontController frontController = new FrontController();
        frontController.dispatchRequest("benz");
        frontController.dispatchRequest("volkswagen");
    }
}
