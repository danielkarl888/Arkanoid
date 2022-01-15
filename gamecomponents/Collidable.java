// ID: 318324563
package gamecomponents;
import geometryprimitives.Point;
import geometryprimitives.Rectangle;

/**
 * Collidable interface will be used by things that can be collided with.
 * The Collidable has method of getting his Rectangle shape, and hit method to identify hit
 * with the collidable and change the velocity of the object who collided with the Collidable.
 */
public interface Collidable {

    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * updating the velocity after hitting the collidable.
     *
     * @param hitter          is the ball that hit the block
     * @param collisionPoint  that an object collided with.
     * @param currentVelocity of the object that hits the collidable.
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
