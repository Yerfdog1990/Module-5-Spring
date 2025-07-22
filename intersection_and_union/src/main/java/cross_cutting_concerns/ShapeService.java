package cross_cutting_concerns;

import demo2.Circle;
import demo2.Shape;
import demo2.Triangle;
import demo2.Rectangle;



public class ShapeService {
    public void describeShape(Shape shape) {
        LoggerConfig.logger.fine("Shape description: " + shape.getClass().getSimpleName());

        switch (shape) {
            case Circle c -> {
                LoggerConfig.logger.fine("Circle radius: " + c.getRadius());
                LoggerConfig.logger.fine("Perimeter: " + c.getPerimeter());
            }
            case Rectangle r -> {
                LoggerConfig.logger.fine("Rectangle width: " + r.getWidth());
                LoggerConfig.logger.fine("Rectangle length: " + r.getLength());
            }
            case Triangle t ->{
                LoggerConfig.logger.fine("Triangle base: " + t.getBase());
                LoggerConfig.logger.fine("Triangle height: " + t.getHeight());
                LoggerConfig.logger.fine("Triangle area: " + t.getArea());
            }
            default -> {
                LoggerConfig.logger.fine("Unknown shape");
            }
        }
        LoggerConfig.logger.fine("Finished describing: " + shape.getClass().getSimpleName());
    }
}
