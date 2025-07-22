package demo2;

import cross_cutting_concerns.LoggerConfig;

// A non-sealed subclass - can be extended freely
public non-sealed class Circle extends Shape implements HasPerimeter{
    private double radius;

    public Circle(double radius) {
        LoggerConfig.logger.fine("Creating a circle with radius: " +radius);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
    @Override
    public double getPerimeter() {
        return 2*Math.PI*radius;
    }
}
