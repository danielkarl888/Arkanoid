// ID: 318324563
package geometryprimitives;
/**
 * A one dimensional point class, which every point has x and y values;
 * can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor.
     * @param x the first coordinate of the point.
     * @param y the second coordinate of the point.
     */
    public Point(double x, double y) {
    this.x = x;
    this.y = y;
    }

    /**
     * @param other point to measure the distance from the current point to other point
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double distance;
        distance = Math.sqrt(Math.pow((this.x - other.x), 2) + Math.pow((this.y - other.y), 2));
        return distance;
    }

    /**
     * @param other point to check if the the current point equals with the other point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * @return the first coordinate of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the first coordinate of the point
     */
    public double getY() {
        return this.y;
    }
}