class Width {
    public static final int SINGLE = 1;
}

class Shape {
    public void draw(int Width) {
        System.out.println("Shape.draw()"+Width);
    }
}

class Circle extends Shape {
    public void draw(Width n) {
        System.out.println("Circle.draw()");
    }
}

public class Test {
    public static void test(Shape s) {
        s.draw(Width.SINGLE);
    }
    public static void main(String[] args) {
        Shape c = new Circle();
        test(c);
    }
}