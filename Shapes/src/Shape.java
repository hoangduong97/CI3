/**
 * Created by AsusA42F on 3/5/2016.
 */
public abstract class Shape {
    private int x;
    private int y;

    public Shape() {
        x = 0;
        y = 0;
    }

    public Shape(int x, int y) {
        this.x = x;
        this.y = y;
    }

    void move() {

    }

    public abstract void draw();

    public abstract double area();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
