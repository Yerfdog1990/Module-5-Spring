package demo2;

import cross_cutting_concerns.LoggerConfig;

// Another final subclass
public final class Triangle extends Shape{
    private double base;
    private double height;

    public Triangle(double base, double height) {
        LoggerConfig.logger.fine("Creating a triangle with base: " +base + " and height: " +height);
        this.base = base;
        this.height = height;
    }

    public double getBase() {
        return base;
    }
    public double getHeight() {
        return height;
    }

    public double getArea() {
        return (base * height) * 0.5;
    }
}
