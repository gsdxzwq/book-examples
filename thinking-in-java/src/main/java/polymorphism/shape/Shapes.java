package polymorphism.shape;

/**
 * Created by zhaowq on 2016/4/13.
 */
public class Shapes {
    private static RandomShapeGenerator gen = new RandomShapeGenerator();

    public static void main(String[] args) {
        Shape[] shapes = new Shape[9];
        // Fill up the array with shapes:
        for (int i = 0; i < shapes.length; i++)
            shapes[i] = gen.next();
        // Make polymorphic method calls:
        for (Shape shape : shapes) shape.draw();
    }
}
