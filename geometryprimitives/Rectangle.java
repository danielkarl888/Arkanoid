// ID: 318324563
package geometryprimitives;
import java.util.ArrayList;
import java.util.List;
/**
 * A Rectangle class which has an upper left point, its width and height.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor
     * Create a new rectangle with location and width/height.
     * @param upperLeft the upper-left point of the rectangle.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * @param line to find the intersection points between the line and the rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> pointsIntersection = new ArrayList<>();
        // create 4 lines to represent the sides of the rectangle.
        Line l1 = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
            this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line l2 = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
            this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Line l3 = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
            this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        Line l4 = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height,
            this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Line[] arr = {l1, l2, l3, l4};
        // add all intersection points between the line and the rectangle and to a list
        for (Line l: arr) {
                if (l.isIntersecting(line)) {
                    pointsIntersection.add(l.intersectionWith(line));
            }
        }
        return pointsIntersection;
    }

    /**
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
}
