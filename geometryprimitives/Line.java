// ID: 318324563
package geometryprimitives;
import java.util.ArrayList;
import java.util.List;
import gamecomponents.CollisionInfo;

/**
 * This Class represents a segment with 2 points: start and end.
 * In addition, the class has some of methods about lines;
 * length, middle point of the line, equality of 2 lines,
 * intersection point between 2 points etc.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructors.
     * @param start point of the line.
     * @param end   point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructors with 4 params.
     * @param x1 first argument of the first point.
     * @param y1 second argument of the first point.
     * @param x2 first argument of the second point.
     * @param y2 second argument of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return length of the line
     */
    public double length() {
        // length is the distance between start point and end point of the line.
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     * @return middle Point of the line by average.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * method to check if the lines intersect or not.
     * @param other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // when the lines are the same line, there is no one intersection.
        if (this.equals(other)) {
            return false;
            // when both lines are vertical to the x or y axis, either they have the same slope - we check if
            // they collinear.
        } else if ((!this.hasSlope() && !other.hasSlope()) || (this.getSlope() == other.getSlope())
            || (this.getSlope() == 0 && other.getSlope() == 0)) {
            return this.start.equals(other.end) || this.end.equals(other.start);
            // when only one of the lines is vertical the the X axis, we check if the vertical intersects the other line
        } else if (!this.hasSlope() && other.hasSlope()) {
            return other.getMaxXInLine() >= this.start.getX() && other.getMinXInLine() <= this.start.getX()
                && this.getMaxYInLine() >= other.getMaxYInLine() && this.getMinYInLine() <= other.getMinYInLine();
        } else if (!other.hasSlope() && this.hasSlope()) {
            return this.getMaxXInLine() >= other.start.getX() && this.getMinXInLine() <= other.start.getX()
                && other.getMaxYInLine() >= this.getMaxYInLine()
                && other.getMinYInLine() <= this.getMinYInLine();
          // when only one of the lines is vertical the the Y axis, we check if the vertical intersects the other line
        } else if (this.getSlope() == 0 && (other.getSlope() != 0 || !other.hasSlope())) {
            return (other.getMinYInLine() <= this.start.getY() && this.start.getY() <= other.getMaxYInLine())
                && (this.verticalToYPointIntersection(other).getX() <= this.getMaxXInLine()
                && this.verticalToYPointIntersection(other).getX() >= this.getMinXInLine());
        } else if (other.getSlope() == 0 && (this.getSlope() != 0 || !this.hasSlope())) {
            return (this.getMinYInLine() <= other.start.getY() && other.start.getY() <= this.getMaxYInLine())
                && (other.verticalToYPointIntersection(this).getX() <= other.getMaxXInLine()
                && other.verticalToYPointIntersection(this).getX() >= other.getMinXInLine());
            //check if the intersection is possible using limits of the lines
        } else if (this.getMaxXInLine() < other.getMinXInLine() || this.getMinXInLine() > other.getMaxXInLine()
            || this.getMaxYInLine() < other.getMinYInLine() || this.getMinYInLine() > other.getMaxYInLine()) {
            return false;
           /*
           now we find the equation of the line using "hasSlopesIntersection" method
           and checking if the intersect Point is on the segment.
           */
        } else {
            Point intersection = this.hasSlopesIntersection(other);
            // check if the intersect Point is on the segment
            return !(intersection.getX() < Math.max(this.getMinXInLine(), other.getMinXInLine())
                || intersection.getX() > Math.min(this.getMaxXInLine(), other.getMaxXInLine()));
        }
    }

    /**
     * finding the intersection point within 2 lines.
     *
     * @param other line.
     * @return the intersection point if the lines intersect,
     * and null otherwise.
     */
    public Point intersectionWith(Line other) {
        // check if there is intersection between the lines
        if (!this.isIntersecting(other)) {
            return null;
            // when the lines are collinear, we return the mutual point.
        } else if ((!this.hasSlope() && !other.hasSlope()) || (this.getSlope() == other.getSlope())
            || (this.getSlope() == 0 && other.getSlope() == 0)) {
            return this.mutualPoint(other);
            //  finding the point intersection when one of the lines is vertical to to x axis.
        } else if (this.hasSlope() && !other.hasSlope()) {
            return this.verticalToXPointIntersection(other);
        } else if (other.hasSlope() && !this.hasSlope()) {
            return other.verticalToXPointIntersection(this);
            //  finding the point intersection when one of the lines is vertical to to Y axis.
        } else if (this.getSlope() == 0 && other.getSlope() != 0) {
            return this.verticalToYPointIntersection(other);
        } else if (this.getSlope() != 0 && other.getSlope() == 0) {
            return other.verticalToYPointIntersection(this);
            //  finding the point intersection when both lines have different slopes and don't vertical to
            //  one of the axes.
        } else if (this.getSlope() != 0 && other.getSlope() != 0) {
            return this.hasSlopesIntersection(other);
        }
        return null;
    }

    /**
     * @param other line.
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
            || (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * @return the slope of the line.
     */
    public double getSlope() {
        // calculate the difference between the x and y params in the line
        double diffY = this.start.getY() - this.end.getY();
        double diffX = this.start.getX() - this.end.getX();
        return diffY / diffX;
    }

    /**
     * @return maximum X argument of the line.
     */
    public double getMaxXInLine() {
        return Math.max(this.start.getX(), this.end.getX());
    }

    /**
     * @return minimum X argument of the line.
     */
    public double getMinXInLine() {
        return Math.min(this.start.getX(), this.end.getX());
    }

    /**
     * @return maximum Y argument of the line.
     */
    public double getMaxYInLine() {
        return Math.max(this.start.getY(), this.end.getY());
    }

    /**
     * @return minimum Y argument of the line.
     */
    public double getMinYInLine() {
        return Math.min(this.start.getY(), this.end.getY());
    }

    /**
     * @return true if the line has slope and false otherwise.
     */
    public boolean hasSlope() {
        return this.start.getX() - this.end.getX() != 0;
    }

    /**
     * @param other line.
     * @return the mutual point of two collinear segments.
     */
    public Point mutualPoint(Line other) {
        // check if the start point of the 2 segments are equal.
        if (this.start.equals(other.start)) {
            return new Point(this.start.getX(), this.start.getY());
            // check if the end point of the first segments is equal to the end point of the other segment.
        } else if (this.end.equals(other.end)) {
            return new Point(this.end.getX(), this.end.getY());
            // check if the start point of the first segments is equal to the end point of the other segment.
        } else if (this.start.equals(other.end)) {
            return new Point(this.start.getX(), this.start.getY());
            // check if the end point of the first segments is equal to the start point of the other segment.
        } else if (this.end.equals(other.start)) {
            return new Point(this.end.getX(), this.end.getY());
        }
        return null;
    }

    /**
     * @param other line that he's vertical to the X axis.
     * @return the intersection point when one of lines is vertical to the X axis.
     * and the other has slope.
     */
    public Point verticalToXPointIntersection(Line other) {
        double m1, b1, intersectXPoint;
        m1 = this.getSlope();
        b1 = this.start.getY() - m1 * this.start.getX();  // finding b in the equation y = mx + b
        intersectXPoint = other.start.getX();
        // return the intersection point according to the equation after finding b.
        return new Point(intersectXPoint, m1 * intersectXPoint + b1);
    }

    /**
     * @param other line that has slope.
     * @return the intersection point when one of lines is vertical to the Y axis,
     * and the other has slope.
     */
    public Point verticalToYPointIntersection(Line other) {
        double m1, b1, intersectYPoint;
        m1 = other.getSlope();
        b1 = other.start.getY() - m1 * other.start.getX(); // finding b in the equation y = mx + b
        intersectYPoint = this.start.getY();
        // return the intersection point according to the equation after finding b.
        return new Point((intersectYPoint - b1) / m1, intersectYPoint);
    }

    /**
     * when both lines have slopes, the function returns the intersection point.
     *
     * @param other the other line.
     * @return intersection point.
     */
    public Point hasSlopesIntersection(Line other) {
        double m1, m2, b1, b2, intersectXPoint, intersectYPoint;
        m1 = this.getSlope();
        m2 = other.getSlope();
        b1 = this.start.getY() - m1 * this.start.getX();    // finding b in first line equation y1 = m1x + b1
        b2 = other.start.getY() - m2 * other.start.getX();  // finding b in second line equation y2 = m2x + b2
        // getting the intersect X and Y Points when we compare the two lines.
        intersectXPoint = (b2 - b1) / (m1 - m2);
        intersectYPoint = m1 * intersectXPoint + b1;
        return new Point(intersectXPoint, intersectYPoint);
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect a rectangle.
     * @return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //
        List<Point> listOfIntersectionPoints = new ArrayList<>(rect.intersectionPoints(this));
        if (listOfIntersectionPoints.isEmpty()) {
            return null;
        } else {
            Point temp = listOfIntersectionPoints.get(0);
            double minDistance = this.start.distance(temp);
            for (Point pnt : listOfIntersectionPoints) {
                if (this.start.distance(pnt) < minDistance) {
                    minDistance = this.start.distance(pnt);
                    temp = pnt;
                }
            }
            return temp;
        }
    }

    /**
     * get the closest collision info that between the start of the line and a list of potential collisions.
     * @param l1 is a list of potential collisions with trajectory line.
     * @return closest Collision info with the start of the line.
     */
    public CollisionInfo closestCollisionInfoToStartOfLine(List<CollisionInfo> l1) {
        double minDistance = l1.get(0).collisionPoint().distance(this.start);
        CollisionInfo temp = l1.get(0);
        for (CollisionInfo element: l1) {
            if (element.collisionPoint().distance(this.start) < minDistance) {
                minDistance = element.collisionPoint().distance(this.start);
                temp = element;
            }
        }
        return temp;
    }
}