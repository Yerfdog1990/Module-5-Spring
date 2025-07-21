package demo2;

// A final subclass
public final class Rectangle extends Shape implements HasPerimeter{
    private double length;
    private double width;

    public Rectangle(double length, double width) {
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
