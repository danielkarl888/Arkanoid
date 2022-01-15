// ID: 318324563
package gamecomponents;
import geometryprimitives.Point;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx is the change in position on the X axis.
     * @param dy is the change in position on the Y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return the dx param of the velocity (the change in position on the X axis)
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * @return the dy param of the velocity (the change in position on the Y axis)
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p point
     * @return the new point after the change
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * returns a new velocity object from angle and speed params.
     * @param angle of the ball.
     * @param speed of the ball.
     * @return new velocity according to the angle and the speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // convert the degree angle into radian angle.
        double newAngle = Math.toRadians(angle);
        // the dx is the sin of the angle multiplied by the speed.
        double dx = speed * Math.sin(newAngle);
        // the dy is the cos of the angle multiplied by the speed.
        double dy = speed * -Math.cos(newAngle);
        return new Velocity(dx, dy);
    }
}