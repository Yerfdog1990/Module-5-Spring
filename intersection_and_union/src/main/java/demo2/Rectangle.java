package demo2;

import cross_cutting_concerns.LoggerConfig;

// A final subclass
public final class Rectangle extends Shape implements HasPerimeter{
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        LoggerConfig.logger.fine("Creating a rectangle with length: " +length + " and width: " +width);
        this.length = length;
        this.width = width;
    }
    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }
    @Override
    public double getPerimeter() {
        return 2 * (length +  width);
    }
}
