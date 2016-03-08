import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Vector;

/**
 * Created by AsusA42F on 3/5/2016.
 */
public class Main {
    public static void main(String[] args) {
        Vector<IArea> vector = new Vector<>();
        vector.add(new Triangle(0, 0, 4, 4, 1, 1));
        vector.add(new Rectangle(0, 0, 4, 5));
        vector.add(new Circle(0, 0, 5));
        vector.add(new Rectangle(1, 1, 2, 3));

        double area = 0;
        for (IArea shape : vector) {
            //System.out.println(shape.getClass().toString());
            if (shape instanceof Rectangle) {
                area += shape.iArea();
            }

            if (shape.getClass().toString().equals("class Rectangle")) {
                area += shape.iArea();
            } // ! shape.getClass().toString() == "class Rectangle"
        }
        System.out.println(area);
    }
}
