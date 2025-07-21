package demo2;

public class ShapeDemo {
    public static void main(String[] args) {
        Shape shape1 = new Circle(2.5);
        Shape shape2 = new Rectangle(4.5,2.5);
        Shape shape3 = new Triangle(2.5,4.0);

        printShapeInfo(shape1);
        printShapeInfo(shape2);
        printShapeInfo(shape3);
    }
    static void printShapeInfo(Shape shape){
        switch (shape){
            case Circle c -> System.out.println("Circle with radius: " +c.getRadius());
            case Rectangle r -> System.out.println("Rectangle with length: " +r.getLength() +" and width: " +r.getWidth());
            case Triangle t -> System.out.print("Triangle with base: " +t.getBase()+ " and height: " +t.getHeight());
            // default is not needed here since all subclasses are sealed
        }

        // Handle intersection type: shape is both Circle and HasPerimeter
        if(shape instanceof Circle && shape instanceof HasPerimeter){
            System.out.printf("Circle has perimeter: %.2f%n", ((Circle)shape).getPerimeter());
        }

        // Handle union type: shape is an instance of HasPerimeter
        if(shape instanceof HasPerimeter){
            if(shape instanceof Rectangle r){
                System.out.println("Rectangle has perimeter: " +r.getPerimeter());
            } else if(shape instanceof Circle c){
                System.out.printf("Circle has perimeter: %.2f%n", c.getPerimeter());
            }
        }
    }
}
