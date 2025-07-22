package cross_cutting_concerns;

import demo2.Circle;
import demo2.Rectangle;
import demo2.Shape;
import demo2.Triangle;

public class ShapeDemo {
    public static void main(String[] args) {
        LoggerConfig.logger.info("Application started");
        ShapeService service = new ShapeService();
        Shape circle = new Circle(7);
        Shape rectangle = new Rectangle(5,8);
        Shape triangle = new Triangle(3,4);

        service.describeShape(circle);
        service.describeShape(rectangle);
        service.describeShape(triangle);
    }
}
