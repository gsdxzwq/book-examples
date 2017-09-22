package polymorphism.shape;

/**
 * Created by zhaowq on 2016/4/13.
 */
public class Circle extends Shape {
    @Override
    public void draw() {
        System.out.println("Circle.draw()");
    }

    @Override
    public void erase() {
        System.out.println("Circle.erase()");
    }
}