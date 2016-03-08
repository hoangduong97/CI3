/**
 * Created by AsusA42F on 3/5/2016.
 */
public class Circle extends Shape implements IArea {
    private int radius;

    public Circle() {
        super(); // goi ham khoi tao khong tham so cua Shape
        this.radius = 0;
    }

    public Circle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }

    @Override
    public double area() {
        return 0;
    }


    @Override
    public double iArea() {
        return 0;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
